package com.ml.core.impl.accesslog;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.ml.core.SlotKeyGenerationStrategy;

public class HttpEventsSlotTest {
	private String DEFAULT_URL = "http://test.url.com";
	private String DEFAULT_HTTP_RETURN_CODE = "200";
	private int SLOT_TIME_UNIT = Calendar.MINUTE;
	private int SLOT_SIZE = 5;
	private SlotKeyGenerationStrategy<HttpEvent> keyStrategy = new MinuteBasedGenerationStrategy();

	@Test
	public void testEventSlotCreation() {

		HttpEvent firstEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);
		HttpEvent secondEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);

		HttpEventsSlot slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));
		Assert.assertEquals(1, slot.getEvents().size());
		slot.addEvent(secondEvent);
		Assert.assertEquals(2, slot.getEvents().size());
	}

	@Test
	public void testEventsOrder() {
		HttpEvent firstEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);
		HttpEvent secondEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);

		HttpEventsSlot slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));
		slot.addEvent(secondEvent);
		Assert.assertTrue(slot.getEvents().toArray(new HttpEvent[] {})[0].equals(firstEvent));
		Assert.assertTrue(slot.getEvents().toArray(new HttpEvent[] {})[1].equals(secondEvent));
	}

	@Test
	public void testMetrics() {
		String url1 = "http://test.url.com";
		String returnCode1 = "200";
		String url2 = "http://test2.url.com";
		String returnCode2 = "400";

		HttpEvent firstEvent = new HttpEvent(Calendar.getInstance(), url1, returnCode1);
		HttpEventsSlot slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));

		slot.addEvent(new HttpEvent(Calendar.getInstance(), url1, returnCode1));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), url1, returnCode1));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), url2, returnCode1));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), url2, returnCode1));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), url1, returnCode2));

		Assert.assertEquals(3, slot.getMetricForEvent(new HttpEvent(Calendar.getInstance(), url1, returnCode1).generateKey()), 0);
		Assert.assertEquals(2, slot.getMetricForEvent(new HttpEvent(Calendar.getInstance(), url2, returnCode1).generateKey()), 0);
		Assert.assertEquals(1, slot.getMetricForEvent(new HttpEvent(Calendar.getInstance(), url1, returnCode2).generateKey()), 0);
		Assert.assertEquals(0, slot.getMetricForEvent(new HttpEvent(Calendar.getInstance(), url2, returnCode2).generateKey()), 0);
	}

	@Test
	public void testAlarming() {
		HttpEvent firstEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);
		HttpEventsSlot slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));

		Assert.assertFalse(slot.isAlarmingForEvent(firstEvent.generateKey()));
		slot.markAlarmingForEvent(firstEvent.generateKey());
		Assert.assertTrue(slot.isAlarmingForEvent(firstEvent.generateKey()));

		slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));
		Assert.assertFalse(slot.isAlarmingForEvent(firstEvent.generateKey()));

		firstEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);
		slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		Assert.assertFalse(slot.isAlarmingForEvent(firstEvent.generateKey()));
		slot.markAlarmingForEvent(firstEvent.generateKey());
		Assert.assertTrue(slot.isAlarmingForEvent(firstEvent.generateKey()));

		firstEvent = new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE);
		slot = new HttpEventsSlot(firstEvent, SLOT_TIME_UNIT, SLOT_SIZE, keyStrategy.generanteKey(firstEvent));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		slot.addEvent(new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		slot.markAlarmingForEvent(firstEvent.generateKey());
		Assert.assertTrue(slot.isAlarmingForEvent(firstEvent.generateKey()));
	}
}
