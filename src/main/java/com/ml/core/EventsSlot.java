package com.ml.core;

import java.util.Calendar;
import java.util.Collection;

import com.ml.tools.DateTools;

public abstract class EventsSlot<T extends Event<T>> {
	protected Collection<T> events;
	protected Calendar start, end;
	protected int slotTimeUnit, slotSize;
	private String slotKey;

	public EventsSlot(Calendar start, int slotTimeUnit, int slotSize, String slotKey) {
		super();
		this.slotTimeUnit = slotTimeUnit;
		this.slotSize = slotSize;
		this.start = DateTools.cloneAndTrunk(start, slotTimeUnit);
		this.end = DateTools.cloneAndAddTime(this.start, slotTimeUnit, slotSize);
		this.slotKey = slotKey;
	}

	public abstract double getMetricForEvent(String eventKey);
	public abstract void addEvent(T e);

	public String getSlotKey() {
		return slotKey;
	}

	public Collection<T> getEvents() {
		return events;
	}

	public Calendar getStart() {
		return start;
	}

	public int getSlotTimeUnit() {
		return slotTimeUnit;
	}

	public int getSlotSize() {
		return slotSize;
	}
}
