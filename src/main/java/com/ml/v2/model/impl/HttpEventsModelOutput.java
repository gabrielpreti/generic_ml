package com.ml.v2.model.impl;

import com.ml.v2.model.api.ModelOutput;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gsantiago on 1/31/15.
 */
public class HttpEventsModelOutput implements ModelOutput {
    private Collection<HttpEventsAnalysisResult> results;

    public HttpEventsModelOutput() {
        results = new ArrayList<HttpEventsAnalysisResult>();
    }

    public void addResult(HttpEventsAnalysisResult result) {
        results.add(result);
    }

    public void addResults(Collection<HttpEventsAnalysisResult> results) {
        this.results.addAll(results);
    }

    public Collection<HttpEventsAnalysisResult> getResults() {
        return this.results;
    }
}
