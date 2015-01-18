/**
 * 
 */
package com.ml.core;

import java.util.Map;

/**
 * @author gsantiago
 *
 */
public abstract class SlottedRepository<T extends Event<T>, X extends EventsSlot<T>, C extends Map<String, X>> implements Repository<T> {
	protected C slots;
	protected int slotWindowSize;
	protected int slotWindowTimeUnit;
	protected SlotKeyGenerationStrategy<T> slotKeyStrategy;

	public SlottedRepository(int slotWindowTimeUnit, int slotWindowSize, SlotKeyGenerationStrategy<T> slotKeyStrategy) {
		super();
		this.slotWindowSize = slotWindowSize;
		this.slotWindowTimeUnit = slotWindowTimeUnit;
		this.slotKeyStrategy = slotKeyStrategy;
	}

	@Override
	public void addEvent(T event) {
		String slotKey = slotKeyStrategy.generanteKey(event);
		if (slots.isEmpty()) {
			slots.put(slotKey, createNewSlot(event, slotWindowTimeUnit, slotWindowSize, slotKey));
			return;
		}

		EventsSlot<T> existentSlot = slots.get(slotKey);
		if (existentSlot == null) {
			slots.put(slotKey, createNewSlot(event, slotWindowTimeUnit, slotWindowSize, slotKey));
		} else {
			existentSlot.addEvent(event);
		}
	}

	protected abstract X createNewSlot(T event, int slotWindowTimeUnit, int slotWindowSize, String slotKey);

}
