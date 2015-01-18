package com.ml.core.impl.accesslog;

import com.ml.core.EventAnalysisResult;

public class HttpEventsAnalysisResult implements EventAnalysisResult {

	public enum AnalysisResultType {
		NOT_ANALYSEBLE, TRAINING, ALARMING, NORMAL
	}

	private AnalysisResultType resultType;
	private double historicalMean, historicalSd, currentMean, currentSd, score;

	private HttpEventsAnalysisResult(AnalysisResultType result, double historicalMean, double historicalSd, double currentMean, double currentSd, double score) {
		this.resultType = result;
		this.historicalMean = historicalMean;
		this.historicalSd = historicalSd;
		this.currentMean = currentMean;
		this.currentSd = currentSd;
		this.score = score;
	}

	public static HttpEventsAnalysisResult notAnalysebleEvent() {
		return new HttpEventsAnalysisResult(AnalysisResultType.NOT_ANALYSEBLE, 0, 0, 0, 0, 0);
	}

	public static HttpEventsAnalysisResult trainingEvent(double historicalMean, double historicalSd, double currentMean, double currentSd, double score) {
		return new HttpEventsAnalysisResult(AnalysisResultType.TRAINING, historicalMean, historicalSd, currentMean, currentSd, score);
	}

	public static HttpEventsAnalysisResult alarmingEvent(double historicalMean, double historicalSd, double currentMean, double currentSd, double score) {
		return new HttpEventsAnalysisResult(AnalysisResultType.ALARMING, historicalMean, historicalSd, currentMean, currentSd, score);
	}

	public static HttpEventsAnalysisResult normalEvent(double historicalMean, double historicalSd, double currentMean, double currentSd, double score) {
		return new HttpEventsAnalysisResult(AnalysisResultType.NORMAL, historicalMean, historicalSd, currentMean, currentSd, score);
	}

	public double getHistoricalMean() {
		return historicalMean;
	}

	public void setHistoricalMean(double historicalMean) {
		this.historicalMean = historicalMean;
	}

	public double getHistoricalSd() {
		return historicalSd;
	}

	public void setHistoricalSd(double historicalSd) {
		this.historicalSd = historicalSd;
	}

	public double getCurrentMean() {
		return currentMean;
	}

	public void setCurrentMean(double currentMean) {
		this.currentMean = currentMean;
	}

	public double getCurrentSd() {
		return currentSd;
	}

	public void setCurrentSd(double currentSd) {
		this.currentSd = currentSd;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public AnalysisResultType getResultType() {
		return resultType;
	}

	public String toString() {
		return resultType.toString();
	}
}
