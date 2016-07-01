package com.barrier.android;

import android.content.Context;

import com.barrier.android.validators.CacheValidator;
import com.barrier.android.validators.OfflineCacheValidator;

/**
 * OFfline cache is in charge to cache data to be used in offline modes in applications.
 */
public class OfflineCache extends SharedPreferencesCache {

    /**
     * Single name to this particular cache.
     */
    public static final String CACHE_NAME = "offlinecache";

    public OfflineCache(Context context) {
        super(CACHE_NAME, new OfflineCacheValidator(), context);
    }


}
