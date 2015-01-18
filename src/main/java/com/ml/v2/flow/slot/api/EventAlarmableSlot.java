package com.ml.v2.flow.slot.api;

import com.ml.v2.flow.event.api.Event;

/**
 * Slots alarmáveis por eventos.
 * 
 * @author gsantiago
 *
 * @param <E>
 *            Tipo do evento que o slot armazena
 */
public interface EventAlarmableSlot<E extends Event> extends Slot<E> {

    boolean isAlarming(E event);

    void markAlarming(E event);
}
