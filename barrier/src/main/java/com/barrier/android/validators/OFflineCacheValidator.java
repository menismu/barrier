package com.barrier.android.validators;

import com.barrier.android.model.CacheEntry;

/**
 * It validates always true as offline entries persist for the entire life.
 */
public class OfflineCacheValidator implements CacheValidator {

    @Override
    public boolean isValid(CacheEntry entry) {
        return entry != null;
    }
}
