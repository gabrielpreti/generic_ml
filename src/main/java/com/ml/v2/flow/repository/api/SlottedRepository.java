package com.ml.v2.flow.repository.api;

import java.util.Collection;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.slot.api.Slot;

/**
 * Repositórios baseados em slots.
 * 
 * @author gsantiago
 *
 * @param <E>
 *            Tipo do evento
 * @param <S>
 *            Tipo do Slot
 */
public interface SlottedRepository<E extends Event, S extends Slot<E>> extends Repository<E> {

	Collection<S> getSlots();
}
