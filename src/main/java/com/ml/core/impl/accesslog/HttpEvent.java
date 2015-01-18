package com.ml.core.impl.accesslog;

import java.util.Calendar;
import java.util.HashMap;

import com.ml.core.Event;

public class HttpEvent extends Event<HttpEvent> {

	private static final String URL_KEY = "url";
	private static final String RETURN_CODE_KEY = "returnCode";

	public HttpEvent(Calendar time, String url, String returnCode) {
		super(time);

		data = new HashMap<String, Object>();
		data.put(URL_KEY, url);
		data.put(RETURN_CODE_KEY, returnCode);
	}

	public String getUrl() {
		return String.valueOf(data.get(URL_KEY));
	}

	public String getReturnCode() {
		return String.valueOf(data.get(RETURN_CODE_KEY));
	}

	public String toString() {
		return String.format("%s\t%s\t%s", time.getTime(), getUrl(), getReturnCode());
	}

	@Override
	public String generateKey() {
		return String.format("%s_%s", getUrl(), getReturnCode());
	}
}
