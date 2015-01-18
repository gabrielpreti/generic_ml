package com.ml.v2.flow.slot.api;

import java.util.Collection;
import java.util.Set;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.event.api.Measurable;

/**
 * Slots que mantém uma indexação dos eventos por Measurable.
 *
 * Created by gsantiago on 1/18/15.
 */
public interface MeasurableSlot<E extends Event> extends Slot<E> {
    Collection<E> getEvents(Measurable m);

    Set<Measurable> getAllMeasurables();
}
