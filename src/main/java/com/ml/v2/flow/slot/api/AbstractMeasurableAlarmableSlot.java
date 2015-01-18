package com.ml.v2.flow.slot.api;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.event.api.Measurable;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementação base para MeasurableAlarmableSlot.
 * 
 * Created by gsantiago on 1/18/15.
 */
public abstract class AbstractMeasurableAlarmableSlot<N extends Number, E extends Event> extends
        AbstractMeasurableMetricsSlot<N, E> implements MeasurableAlarmableSlot<E> {

    private Set<Measurable> alarmingEvents;

    protected AbstractMeasurableAlarmableSlot() {
        alarmingEvents = new HashSet<Measurable>();
    }

    @Override
    public boolean isAlarming(Measurable m) {
        return alarmingEvents.contains(m);
    }

    @Override
    public void markAlarming(Measurable m) {
        alarmingEvents.add(m);
    }
}
