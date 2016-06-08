package com.barrier.android;

import com.barrier.android.exceptions.CacheEntryExpiredException;
import com.barrier.android.exceptions.CacheEntryNotFoundException;

/**
 * Cache implementations let handle data stored.
 */
public interface Cache {

    /**
     * It gets from the cache the value or entry for the given key or name.
     *
     * @param key Key or name of the entry to get from the cache.
     * @return The value stored in the cache for the given value.
     * @throws CacheEntryNotFoundException
     * @throws CacheEntryExpiredException
     */
    String get(String key) throws CacheEntryNotFoundException, CacheEntryExpiredException;

    /**
     * It saves a new entry or value in the cache assigning the given key. If the key already exists in the cache the value will be overridden.
     *
     * @param key   Key or name of the entry to be placed.
     * @param value Value to be stored and associated to the given key.
     */
    void put(String key, String value);

    /**
     * It invalidates the given key in the cache. If the key does not exist in the cache nothing will be invalidated.
     *
     * @param key Name or key of the entry to be invalidated.
     */
    void invalidate(String key);

    /**
     * It invalidates all the entries / keys in the cache.
     */
    void invalidateAll();
}
