package com.ml.core;

import java.util.Calendar;
import java.util.Map;

public abstract class Event<T extends Event<T>> implements Comparable<T> {

	protected Map<String, Object> data;
	protected Calendar time;

	public abstract String generateKey();

	public Event(Calendar time) {
		super();
		this.time = time;
	}

	@Override
	public int compareTo(T o) {
		return time.compareTo(o.time);
	}

	public Calendar getTime() {
		return time;
	}

}
