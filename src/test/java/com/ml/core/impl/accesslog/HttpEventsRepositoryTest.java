package com.ml.core.impl.accesslog;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.ml.core.SlotKeyGenerationStrategy;

public class HttpEventsRepositoryTest {
	private int SLOT_WINDOW_SIZE = 5;
	private String DEFAULT_URL = "http://test.url.com";
	private String DEFAULT_HTTP_RETURN_CODE = "200";
	private SlotKeyGenerationStrategy<HttpEvent> keyStrategy = new MinuteBasedGenerationStrategy();

	@Test
	public void testeRepositoryCreation() {
		new HttpEventsRepository(SLOT_WINDOW_SIZE, keyStrategy);
	}

	@Test
	public void testEventsAddition() {
		HttpEventsRepository repository = new HttpEventsRepository(SLOT_WINDOW_SIZE, keyStrategy);

		Calendar firstSlotTime = Calendar.getInstance();
		repository.addEvent(new HttpEvent(firstSlotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		Assert.assertEquals(1, repository.getAllSlots().size());
		Assert.assertEquals(1, repository.getAllEvents().size());

		repository.addEvent(new HttpEvent(Calendar.getInstance(), DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		Assert.assertEquals(1, repository.getAllSlots().size());
		Assert.assertEquals(2, repository.getAllEvents().size());

		Calendar secondSlotTime = Calendar.getInstance();
		secondSlotTime.add(Calendar.MINUTE, SLOT_WINDOW_SIZE + 1);
		repository.addEvent(new HttpEvent(secondSlotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		Assert.assertEquals(2, repository.getAllSlots().size());
		Assert.assertEquals(3, repository.getAllEvents().size());

		Calendar thirdSlotTime = Calendar.getInstance();
		thirdSlotTime.add(Calendar.MINUTE, 2 * SLOT_WINDOW_SIZE + 1);
		repository.addEvent(new HttpEvent(thirdSlotTime, DEFAULT_URL, DEFAULT_HTTP_RETURN_CODE));
		Assert.assertEquals(3, repository.getAllSlots().size());
		Assert.assertEquals(4, repository.getAllEvents().size());
	}
}
