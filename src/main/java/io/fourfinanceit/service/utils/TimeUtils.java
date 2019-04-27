package io.fourfinanceit.service.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeUtils {

	private static final int SECONDS_IN_DAY = 86400;

	public long getCurrentTimestampInDays() {
		return System.currentTimeMillis() / (1000 * SECONDS_IN_DAY);
	}

	public int getCurrentTimeInHours() {
		return LocalDateTime.now().getHour();
	}
}
