package com.ml.v2.flow.slot.api;

import com.ml.v2.flow.event.api.Event;

/**
 * Slots alarmáveis
 * 
 * Created by gsantiago on 1/18/15.
 */
public interface AlarmableSlot<E extends Event> extends Slot<E> {
    boolean isAlarming();

    void markAlarming();
}
