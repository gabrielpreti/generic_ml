package com.ml.v2.flow.repository.impl;

import java.text.SimpleDateFormat;

import com.ml.v2.flow.event.impl.HttpEvent;
import com.ml.v2.flow.repository.api.SlotKeyGenerationStrategy;

/**
 * Created by gsantiago on 1/17/15.
 */
public class MinuteBasedGenerationStrategy implements SlotKeyGenerationStrategy<HttpEvent> {

    @Override
    public String generateKey(HttpEvent event) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(event.getTime().getTime());
    }
}
