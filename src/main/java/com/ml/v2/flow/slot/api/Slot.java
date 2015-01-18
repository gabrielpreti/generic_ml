package com.ml.v2.flow.slot.api;

import java.util.Collection;

import com.ml.v2.flow.event.api.Event;

/**
 * Interface base para slots.
 * 
 * @author gsantiago
 *
 * @param <E>
 */
public interface Slot<E extends Event> {

    void addEvent(E e);

    Collection<E> getEvents();

    String getKey();
}
