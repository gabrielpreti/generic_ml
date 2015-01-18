package com.ml.v2.flow.event.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ml.v2.flow.event.api.Measurable;
import com.ml.v2.flow.event.api.TimedEvent;

/**
 * Implementação para eventos http.
 * 
 * @author gsantiago
 *
 */
public class HttpEvent implements TimedEvent {

    private static final String URL_KEY = "url";
    private static final String RETURN_CODE_KEY = "returnCode";

    private Date time;
    protected Map<String, String> data;

    public HttpEvent(Date time, String url, String returnCode) {
        this.time = time;
        data = new HashMap<String, String>();
        data.put(URL_KEY, url);
        data.put(RETURN_CODE_KEY, returnCode);
    }

    public String getUrl() {
        return data.get(URL_KEY);
    }

    public String getReturnCode() {
        return data.get(RETURN_CODE_KEY);
    }

    @Override
    public Date getTime() {
        return this.time;
    }

    @Override
    public Measurable getMeasurable() {
        return new UrlReturnCodeMeasurable(getUrl(), getReturnCode());
    }

    @Override
    public String toString() {
        return String.format("%s\t%s", getTime(), getMeasurable());
    }
}
