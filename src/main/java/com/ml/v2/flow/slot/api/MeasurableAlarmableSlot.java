package com.ml.v2.flow.slot.api;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.event.api.Measurable;

/**
 * Slots alarmáveis individualmente por Measurables
 *
 * Created by gsantiago on 1/18/15.
 */
public interface MeasurableAlarmableSlot<E extends Event> extends MeasurableSlot<E> {
    boolean isAlarming(Measurable event);

    void markAlarming(Measurable event);
}
