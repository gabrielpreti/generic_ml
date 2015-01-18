package com.ml.v2.flow.repository.api;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.event.impl.HttpEvent;

public interface SlotKeyGenerationStrategy<E extends Event> {

    String generateKey(E e);
}
