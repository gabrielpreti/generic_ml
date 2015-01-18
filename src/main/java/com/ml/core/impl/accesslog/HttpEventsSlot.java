package com.ml.core.impl.accesslog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ml.core.EventsSlot;

public class HttpEventsSlot extends EventsSlot<HttpEvent> {

	private Map<String, Integer> metrics = new HashMap<String, Integer>();
	private Set<String> alarmingEvents = new HashSet<String>();

	public HttpEventsSlot(HttpEvent startEvent, int slotTimeUnit, int slotSize, String slotKey) {
		super(startEvent.getTime(), slotTimeUnit, slotSize, slotKey);

		this.events = new ArrayList<HttpEvent>();
		addEvent(startEvent);
	}

	@Override
	public double getMetricForEvent(String eventKey) {
		Integer metric = metrics.get(eventKey);
		return metric == null ? 0 : metric.doubleValue();
	}

	public void addEvent(HttpEvent e) {
		events.add(e);

		String eventKey = e.generateKey();
		if (metrics.containsKey(eventKey)) {
			metrics.put(eventKey, metrics.get(eventKey) + 1);
		} else {
			metrics.put(eventKey, 1);
		}
	}

	public boolean isAlarmingForEvent(String eventKey) {
		return alarmingEvents.contains(eventKey);
	}

	public void markAlarmingForEvent(String eventKey) {
		alarmingEvents.add(eventKey);
	}

}
