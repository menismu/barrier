package com.barrier.android.validators;

import com.barrier.android.model.CacheEntry;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * It validates entries based on the time where the entry was created and a common valid period of all entries.
 */
public class APICacheValidator implements CacheValidator {

    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmm";

    /**
     * Default expiration time for cache is set as 1 hour.
     */
    private static final int DEFAULT_EXPIRATION_TIME = 1;

    private DateTimeFormatter mDateFormatter;

    private int mExpirationTime;

    public APICacheValidator() {
        this(DEFAULT_DATE_FORMAT, DEFAULT_EXPIRATION_TIME);
    }

    public APICacheValidator(String dateFormat) {
        this(dateFormat, DEFAULT_EXPIRATION_TIME);
    }

    public APICacheValidator(int expirationTime) {
        this(DEFAULT_DATE_FORMAT, expirationTime);
    }

    public APICacheValidator(String dateFormat, int expirationTime) {
        mDateFormatter = DateTimeFormat.forPattern(dateFormat);
        mExpirationTime = expirationTime;
    }

    @Override
    public boolean isValid(CacheEntry entry) {
        return mDateFormatter.parseDateTime(entry.getDate()).plusHours(mExpirationTime).isAfterNow();
    }
}


