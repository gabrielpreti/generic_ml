package com.ml.core.impl.accesslog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.ml.core.SlotKeyGenerationStrategy;
import com.ml.core.SlottedRepository;

public class HttpEventsRepository extends SlottedRepository<HttpEvent, HttpEventsSlot, Map<String, HttpEventsSlot>> {

	public HttpEventsRepository(int slotWindowInMinutes, SlotKeyGenerationStrategy<HttpEvent> slotKeyStrategy) {
		super(Calendar.MINUTE, slotWindowInMinutes, slotKeyStrategy);

		slots = new TreeMap<String, HttpEventsSlot>();
	}

	@Override
	public Collection<HttpEvent> getAllEvents() {
		List<HttpEvent> allEvents = new ArrayList<HttpEvent>();
		for (HttpEventsSlot slot : slots.values()) {
			allEvents.addAll(slot.getEvents());
		}
		return allEvents;
	}

	@Override
	protected HttpEventsSlot createNewSlot(HttpEvent event, int slotWindowTimeUnit, int slotWindowSize, String slotKey) {
		return new HttpEventsSlot(event, slotWindowTimeUnit, slotWindowSize, slotKey);
	}

	public Set<Entry<String, HttpEventsSlot>> getAllSlots() {
		return slots.entrySet();
	}

	public void printSlots() throws FileNotFoundException {
		for (Entry<String, HttpEventsSlot> entry : slots.entrySet()) {
			PrintWriter writer = new PrintWriter(new File("/tmp/slots/", "slot_" + entry.getKey()));

			for (HttpEvent event : entry.getValue().getEvents()) {
				writer.println(event);
			}
			writer.flush();
			writer.close();
		}
	}

	public void printSlotsReport() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File("/tmp/", "report.csv"));
		for (Entry<String, HttpEventsSlot> entry : slots.entrySet()) {
			writer.println(entry.getKey() + ";" + entry.getValue().getEvents().size());
		}
		writer.flush();
		writer.close();
	}
}
