package com.ml.v2.model.impl;

import com.ml.core.EventAnalysisResult;
import com.ml.v2.flow.event.api.Measurable;
import com.ml.v2.flow.event.impl.UrlReturnCodeMeasurable;
import com.ml.v2.model.api.ModelOutput;

public class HttpEventsAnalysisResult {

    public enum AnalysisResultType {
        NOT_ANALYSEBLE, TRAINING, ALARMING, NORMAL
    }

    private AnalysisResultType resultType;
    private double historicalMean, historicalSd, currentMean, currentSd, score;
    private Measurable eventMeasure;
    private String slotKey;

    private HttpEventsAnalysisResult(AnalysisResultType result, double historicalMean, double historicalSd,
            double currentMean, double currentSd, double score, Measurable eventMeasure, String slotKey) {
        this.resultType = result;
        this.historicalMean = historicalMean;
        this.historicalSd = historicalSd;
        this.currentMean = currentMean;
        this.currentSd = currentSd;
        this.score = score;
        this.eventMeasure = eventMeasure;
        this.slotKey = slotKey;
    }

    public static HttpEventsAnalysisResult notAnalysebleEvent(Measurable eventMeasure, String slotKey) {
        return new HttpEventsAnalysisResult(AnalysisResultType.NOT_ANALYSEBLE, 0, 0, 0, 0, 0, eventMeasure, slotKey);
    }

    public static HttpEventsAnalysisResult trainingEvent(double historicalMean, double historicalSd,
            double currentMean, double currentSd, double score, Measurable eventMeasure, String slotKey) {
        return new HttpEventsAnalysisResult(AnalysisResultType.TRAINING, historicalMean, historicalSd, currentMean,
                currentSd, score, eventMeasure, slotKey);
    }

    public static HttpEventsAnalysisResult alarmingEvent(double historicalMean, double historicalSd,
            double currentMean, double currentSd, double score, Measurable eventMeasure, String slotKey) {
        return new HttpEventsAnalysisResult(AnalysisResultType.ALARMING, historicalMean, historicalSd, currentMean,
                currentSd, score, eventMeasure, slotKey);
    }

    public static HttpEventsAnalysisResult normalEvent(double historicalMean, double historicalSd, double currentMean,
            double currentSd, double score, Measurable eventMeasure, String slotKey) {
        return new HttpEventsAnalysisResult(AnalysisResultType.NORMAL, historicalMean, historicalSd, currentMean,
                currentSd, score, eventMeasure, slotKey);
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
