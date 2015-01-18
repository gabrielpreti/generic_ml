package com.ml.core.impl.accesslog;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpEventTest {

	private Calendar firstEventTime, secondEventTime;
	private String url, returnCode;

	@Before
	public void beforeTest() {
		firstEventTime = Calendar.getInstance();
		secondEventTime = Calendar.getInstance();
		secondEventTime.add(Calendar.SECOND, 1);
		url = "http://test.url.com";
		returnCode = "200";
	}

	@Test
	public void testEventCreation() {
		HttpEvent event = new HttpEvent(firstEventTime, url, returnCode);

		Assert.assertEquals(firstEventTime, event.getTime());
		Assert.assertEquals(url, event.getUrl());
		Assert.assertEquals(returnCode, event.getReturnCode());
	}

	@Test
	public void testEventComparing() {
		HttpEvent firstEvent = new HttpEvent(firstEventTime, url, returnCode);
		HttpEvent secondEvent = new HttpEvent(secondEventTime, url, returnCode);

		Assert.assertTrue(firstEvent.compareTo(secondEvent) < 0);
		Assert.assertTrue(secondEvent.compareTo(firstEvent) > 0);
		Assert.assertTrue(firstEvent.compareTo(firstEvent) == 0);
	}
}
