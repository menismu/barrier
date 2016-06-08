package com.barrier.android.validators;

import com.barrier.android.model.CacheEntry;

/**
 * Implementations of CacheValidator interface let check if entries are valid or not expired.
 */
public interface CacheValidator {

    /**
     * It checks the given entry is valid
     *
     * @param entry Entry data.
     * @return True if the entry is still valid, false otherwise.
     */
    boolean isValid(CacheEntry entry);
}

