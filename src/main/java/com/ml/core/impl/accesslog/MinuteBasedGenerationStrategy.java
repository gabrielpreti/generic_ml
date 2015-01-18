package com.ml.core.impl.accesslog;

import java.text.SimpleDateFormat;

import com.ml.core.SlotKeyGenerationStrategy;

public class MinuteBasedGenerationStrategy implements SlotKeyGenerationStrategy<HttpEvent> {

	@Override
	public String generanteKey(HttpEvent event) {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(event.getTime().getTime());
	}

}
