package io.fourfinanceit.service.validator;

import io.fourfinanceit.exception.ForFinanceObjectContainsNullFieldException;

import java.util.Arrays;
import java.util.Objects;

public interface Validator {
	default void checkContainsNullField(Object... fields) {
		if (Arrays.stream(fields).anyMatch(Objects::isNull)) {
			throw new ForFinanceObjectContainsNullFieldException("Some of not null fields is null");
		}
	}
}
