package com.ml.core.impl.accesslog;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.ml.core.impl.accesslog.HttpEventsAnalysisResult.AnalysisResultType;

public class HttpEventsModelTest {
	private int SLOT_WINDOW_SIZE = 5;
	private String DEFAULT_URL = "http://test.url.com";
	private String DEFAULT_HTTP_RETURN_CODE = "200";
	private int HISTORY_SIZE = 15;
	private int DELAY = 5;

	/**
	 * Faz um teste só, pois tudo depende da geração do modelo.
	 */
	//FIXME: definir como testar o modelo
//	@Test
//	public void testModel() {
//
//		HttpEventsRepository repository = new HttpEventsRepository(SLOT_WINDOW_SIZE, new MinuteBasedGenerationStrategy());
//
//		HttpEventsModel model = new HttpEventsModel(repository, 0, HISTORY_SIZE, DELAY);
//
//		// Controlamos os tempo para gerar cada evento em um slot, ou seja, a cada novo evento o slot anterior é fechado.
//		// Primeiros 20 eventos, ainda não analisáveis
//		int i = 0;
//		for (; i < (HISTORY_SIZE + DELAY); i++) {
//			Calendar slotTime = Calendar.getInstance();
//			slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 1);
//			HttpEventsAnalysisResult result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//			Assert.assertEquals(AnalysisResultType.NOT_ANALYSEBLE, result.getResultType());
//		}
//
//		// Aqui já temos um histórico mínimo, podemos começar a treinar
//		model.startTraining();
//
//		// Controlamos os tempo para gerar cada evento em um slot, ou seja, a cada novo evento o slot anterior é fechado.
//		// Como ainda estamos em treino, todos os eventos devolvem TRAINING
//		for (; i < HISTORY_SIZE + DELAY + 20; i++) {
//			Calendar slotTime = Calendar.getInstance();
//			slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 1);
//			HttpEventsAnalysisResult result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//			Assert.assertEquals(AnalysisResultType.TRAINING, result.getResultType());
//			Assert.assertEquals(1, result.getHistoricalMean(), 0.0001);
//			Assert.assertEquals(0, result.getHistoricalSd(), 0.0001);
//			Assert.assertEquals(0, result.getCurrentMean(), 0.0001);
//			Assert.assertEquals(0, result.getCurrentSd(), 0.0001);
//			Assert.assertEquals(0, result.getScore(), 0.0001);
//		}
//
//		// Paramos o treino
//		model.stopTraining();
//
//		// Controlamos os tempo para gerar cada evento em um slot, ou seja, a cada novo evento o slot anterior é fechado.
//		// Cada slot possui somente um evento, logo os slots estão todos normais
//		for (; i < HISTORY_SIZE + DELAY + 40; i++) {
//			Calendar slotTime = Calendar.getInstance();
//			slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 1);
//			HttpEventsAnalysisResult result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//			Assert.assertEquals(AnalysisResultType.NORMAL, result.getResultType());
//			Assert.assertEquals(1, result.getHistoricalMean(), 0.0001);
//			Assert.assertEquals(0, result.getHistoricalSd(), 0.0001);
//			Assert.assertEquals(0, result.getCurrentMean(), 0.0001);
//			Assert.assertEquals(0, result.getCurrentSd(), 0.0001);
//			Assert.assertEquals(0, result.getScore(), 0.0001);
//		}
//
//		// Fechamos o último slot com 1 evento, agora vamos começar a gerar mais de um evento por slot
//		Calendar slotTime = Calendar.getInstance();
//		slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 1);
//		HttpEventsAnalysisResult result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//		Assert.assertEquals(AnalysisResultType.NORMAL, result.getResultType());
//		Assert.assertEquals(1, result.getHistoricalMean(), 0.0001);
//		Assert.assertEquals(0, result.getHistoricalSd(), 0.0001);
//		Assert.assertEquals(0, result.getCurrentMean(), 0.0001);
//		Assert.assertEquals(0, result.getCurrentSd(), 0.0001);
//		Assert.assertEquals(0, result.getScore(), 0.0001);
//
//		// Vários slots com mais de um evento por slot
//		for (; i < HISTORY_SIZE + DELAY + 60; i++) {
//			int iterations = (int) Math.ceil(Math.random() * 2) + 1; // A quantidade por slot é randômica entre 1 e 3
//			for (int j = 0; j < iterations; j++) {
//				slotTime = Calendar.getInstance();
//				slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 2);
//				result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//				Assert.assertEquals(AnalysisResultType.NOT_ANALYSEBLE, result.getResultType());
//			}
//
//			slotTime = Calendar.getInstance();
//			slotTime.add(Calendar.MINUTE, (i + 1) * SLOT_WINDOW_SIZE + 1);
//			result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//			Assert.assertEquals(AnalysisResultType.NORMAL, result.getResultType());
//		}
//
//		// Geramos um único slot com 500 eventos.
//		for (int j = 0; j < 500; j++) {
//			slotTime = Calendar.getInstance();
//			slotTime.add(Calendar.MINUTE, i * SLOT_WINDOW_SIZE + 2);
//			result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//			Assert.assertEquals(AnalysisResultType.NOT_ANALYSEBLE, result.getResultType());
//		}
//		slotTime = Calendar.getInstance();
//		slotTime.add(Calendar.MINUTE, (i + 1) * SLOT_WINDOW_SIZE + 1);
//		result = model.analyseEvent(new HttpEvent(slotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
//		
//		// Espera-se que o slot com 500 eventos gere um alarme
//		Assert.assertEquals(AnalysisResultType.ALARMING, result.getResultType());
//
//	}
}
