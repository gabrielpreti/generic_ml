package com.ml.core;

public interface SlotKeyGenerationStrategy<T extends Event<T>> {

	String generanteKey(T event);
}
