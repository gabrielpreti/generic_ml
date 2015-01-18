package com.ml.core.impl.accesslog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ml.core.Model;
import com.ml.tools.Stats;

public class HttpEventsModel extends Model<HttpEvent, HttpEventsAnalysisResult> {
	private static final Logger log = LoggerFactory.getLogger(HttpEventsModel.class);

	private int trainingSize, historySize, delay;
	private HttpEventsRepository repository;
	private double higherScore;

	private final double THRESOLD_REL_SD_MEAN = 0.2d;

	public HttpEventsModel(HttpEventsRepository repository, int trainingSize, int historySize, int delay) {
		super();

		this.repository = repository;
		this.trainingSize = trainingSize;
		this.historySize = historySize;
		this.delay = delay;
		this.higherScore = Double.MIN_VALUE;
	}

	@Override
	public void runRepositoryAnalysis() {
		log.info("Identifing unique events ...");
		Set<String> uniqueMetrics = new HashSet<String>();
		Collection<HttpEvent> events = repository.getAllEvents();
		for (HttpEvent event : events) {
			uniqueMetrics.add(event.generateKey());
		}
		log.info(String.format("Identified %d unique events", uniqueMetrics.size()));

		// TEMPORÁRIO
		uniqueMetrics = new HashSet<String>();
		uniqueMetrics.add("pagseguro.uol.com.br/index.jhtml_200");

		for (String uniqueEvent : uniqueMetrics) {
			executeUniqueEventKeyAnalysis(uniqueEvent);
		}
	}

	private void executeUniqueEventKeyAnalysis(String eventKey) {
		log.info(String.format("Analysing eventy unique key %s", eventKey));
		EventAnalysisStack stack = new EventAnalysisStack(historySize, delay);

		for (Entry<String, HttpEventsSlot> entry : repository.getAllSlots()) {
			HttpEventsSlot currentSlot = entry.getValue();
			
			stack.addSlot(currentSlot);
			log.info(String.format("\tAnalysing slot starting at %s", entry.getKey()));

			if (stack.countSlots() < (historySize + delay)) {
				log.info(String.format("\tSlot %s for metric %s is %s", entry.getKey(), eventKey, HttpEventsAnalysisResult.notAnalysebleEvent()));
				continue;
			}

			double[] delayMetrics = stack.getCurrentDelayMetrics(eventKey);
			double[] historyMetrics = stack.getCurrentHistorySlots(eventKey);

			double historicalMean = Stats.mean(historyMetrics);
			double historicalSd = Stats.sdev(historyMetrics);

			// Se média histórica ou o desvio padrão histórico for igual a 0, não da pra analisar e portanto consideramos normal
			if (Stats.isEqual(historicalMean, 0d, 0.001d) || Stats.isEqual(historicalSd, 0d, 0.001d)) {
				log.debug("\tMean or sd equal to 0. Ignoring event.");
				log.info(String.format("\tSlot %s for metric %s is %s", entry.getKey(), eventKey, HttpEventsAnalysisResult.normalEvent(historicalMean, historicalSd, 0, 0, 0)));
				continue;
			}

			double currentMean = Stats.mean(delayMetrics);
			double currentSd = Stats.sdev(delayMetrics);
			double difference = Math.abs(currentMean - historicalMean);

			// O score é uma medida da relação entre o valor absoluto da diferença entre as médias com o desvio padrão: em situações normais, o valor absoluto da diferença entre as
			// médias deveria estar dentro da marge de desvio padrão. Quanto maior esse valor for em relação ao desvio padrão, maior o indício de anormalidade.
			// double scoreCurrentSd = difference / currentSd;
			double scoreHistoricalSd = difference / historicalSd;
			boolean isHistoricalVariationSmallEnough = historicalSd / historicalMean < THRESOLD_REL_SD_MEAN; // Se os dados históricos tiverem uma variação muito grande, não tem como analisar.
			boolean isDifferenceGreatherThanHistoricalScore = scoreHistoricalSd > higherScore;

			if (stack.countSlots() < (trainingSize + historySize + delay)) {
				log.info(String.format("\tSlot %s for metric %s is %s", entry.getKey(), eventKey, HttpEventsAnalysisResult.trainingEvent(historicalMean, historicalSd, currentMean, currentSd, scoreHistoricalSd)));
				higherScore = Stats.max(higherScore, scoreHistoricalSd);
				continue;
			}

			if (isHistoricalVariationSmallEnough && isDifferenceGreatherThanHistoricalScore) {
				currentSlot.markAlarmingForEvent(eventKey);
				log.info(String.format("\tSlot %s for metric %s is %s", entry.getKey(), eventKey, HttpEventsAnalysisResult.alarmingEvent(historicalMean, historicalMean, currentMean, currentSd, scoreHistoricalSd)));
			} else {
				higherScore = Stats.max(higherScore, scoreHistoricalSd);
				log.info(String.format("\tSlot %s for metric %s is %s", entry.getKey(), eventKey, HttpEventsAnalysisResult.normalEvent(historicalMean, historicalMean, currentMean, currentSd, scoreHistoricalSd)));
			}
		}
	}

	public class EventAnalysisStack {
		private List<HttpEventsSlot> analysedSlots;
		private int historySize, delaySize;

		public EventAnalysisStack(int historySize, int delaySize) {
			this.historySize = historySize;
			this.delaySize = delaySize;
			this.analysedSlots = new ArrayList<HttpEventsSlot>();
		}

		public int countSlots() {
			return analysedSlots.size();
		}

		public void addSlot(HttpEventsSlot slot) {
			analysedSlots.add(slot);
		}

		public double[] getCurrentDelayMetrics(String eventKey) {
			double[] metrics = new double[delaySize];

			ListIterator<HttpEventsSlot> backwardIterator = analysedSlots.listIterator(analysedSlots.size());
			for (int i = 0; i < delaySize; i++) {
				metrics[i] = backwardIterator.previous().getMetricForEvent(eventKey);
			}

			return metrics;
		}

		public double[] getCurrentHistorySlots(String eventKey) {
			double[] metrics = new double[historySize];

			ListIterator<HttpEventsSlot> backwardIterator = analysedSlots.listIterator(analysedSlots.size());
			for (int i = 0; i < delaySize; i++) {
				backwardIterator.previous();
			}

			int addedEvents = 0;
			while (addedEvents < historySize) {
				HttpEventsSlot previousSlot = backwardIterator.previous();
				if (!previousSlot.isAlarmingForEvent(eventKey)) {
					metrics[addedEvents] = previousSlot.getMetricForEvent(eventKey);
					addedEvents++;
				}
			}
			return metrics;
		}
	}
}
