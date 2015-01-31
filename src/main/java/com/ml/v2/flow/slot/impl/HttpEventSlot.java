package com.ml.v2.flow.slot.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.ml.v2.flow.event.api.Measurable;
import com.ml.v2.flow.event.impl.HttpEvent;
import com.ml.v2.flow.slot.api.AbstractMeasurableAlarmableSlot;

/**
 * Implementação para slots de evento http. Os eventos estão ordenados por ordem de inserção (a implementação é um
 * ArrayList).
 * 
 * @author gsantiago
 *
 */
public class HttpEventSlot extends AbstractMeasurableAlarmableSlot<Integer, HttpEvent> {

    private String slotKey;

    public HttpEventSlot(String slotKey, HttpEvent firstEvent) {
        this.slotKey = slotKey;
        addEvent(firstEvent);
    }

    @Override
    protected void updateMetric(HttpEvent e) {
        Measurable m = e.getMeasurable();
        if (metrics.containsKey(m)) {
            metrics.put(m, metrics.get(m) + 1);
        } else {
            metrics.put(m, 1);
        }

    }

    @Override
    protected Collection<HttpEvent> instantiateEvents() {
        return Collections.synchronizedList(new ArrayList<HttpEvent>());
    }

    public Integer getDefaultMetricValue() {
        return 0;
    }

    @Override
    public String getKey() {
        return slotKey;
    }
}
