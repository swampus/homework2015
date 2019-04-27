package io.fourfinanceit.service.utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TimeUtilsUnitTest {
	private TimeUtils loanServerTimeUtils = new TimeUtils();

	@Test
	public void testGetCurrentTimestampInDays() throws Exception {
		assertEquals(System.currentTimeMillis() / (1000 * 86400),
				loanServerTimeUtils.getCurrentTimestampInDays());
	}

	@Test
	public void testGetCurrentTimeInHours() throws Exception {
		assertEquals(LocalDateTime.now().getHour(),
				loanServerTimeUtils.getCurrentTimeInHours());
	}
}