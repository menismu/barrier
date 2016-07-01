package com.barrier.android;

import android.content.Context;

import com.barrier.android.validators.APICacheValidator;

/**
 * API cache is in charge to cache data from API calls to web services or APIs.
 */
public final class APICache extends SharedPreferencesCache {

    /**
     * Single name to this particular cache.
     */
    public static final String CACHE_NAME = "apicache";

    public APICache(Context context) {
        super(CACHE_NAME, new APICacheValidator(), context);
    }
}
