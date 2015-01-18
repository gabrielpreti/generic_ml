package com.ml.core;

import java.util.Collection;

public interface Repository<T extends Event<T>> {

	void addEvent(T event);

	Collection<T> getAllEvents();
}
