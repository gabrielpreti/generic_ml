package com.ml.v2.flow.repository.api;

import java.util.Collection;

import com.ml.v2.flow.event.api.Event;

/**
 * Classe base para repositórios
 * 
 * @author gsantiago
 *
 * @param <E>
 *            Tipo do evento do repositório
 */
public interface Repository<E extends Event> {
	void addEvent(E event);

	Collection<E> getEvents();
}
