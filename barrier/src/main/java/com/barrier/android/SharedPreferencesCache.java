package com.barrier.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.barrier.android.exceptions.CacheEntryExpiredException;
import com.barrier.android.exceptions.CacheEntryNotFoundException;
import com.barrier.android.model.CacheEntry;
import com.barrier.android.validators.CacheValidator;
import com.google.gson.Gson;

import org.joda.time.DateTime;

public class SharedPreferencesCache implements Cache {

    private static final String DEFAULT_VALUE = "{}";

    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmm";

    private String mCacheName;

    private CacheValidator mCacheValidator;

    private Context mContext;

    public SharedPreferencesCache(String cacheName, CacheValidator cacheValidator, Context context) {
        this.mCacheName = cacheName;
        this.mCacheValidator = cacheValidator;
        this.mContext = context;
    }

    @Override
    public String get(String key) throws CacheEntryNotFoundException, CacheEntryExpiredException {

        if (!contains(key)) {
            throw new CacheEntryNotFoundException("No entry found [name = " + mCacheName + ", key = " + key + "]");
        }

        String entryString = mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE).getString(key, DEFAULT_VALUE);
        CacheEntry entry = new Gson().fromJson(entryString, CacheEntry.class);

        if (!mCacheValidator.isValid(entry)) {
            throw new CacheEntryExpiredException("Cache entry with given key has expired");
        }

        return entry.getValue();
    }

    @Override
    public void put(String key, String value) {
        CacheEntry entry = new CacheEntry();
        entry.setDate(DateTime.now().toString(DEFAULT_DATE_FORMAT));
        entry.setKey(key);
        entry.setValue(value);

        String entryString = new Gson().toJson(entry);
        SharedPreferences.Editor editor = mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE).edit();

        editor.putString(entry.getKey(), entryString);
        editor.commit();
    }

    @Override
    public void invalidate(String key) {

        if (!contains(key)) {
            return;
        }

        SharedPreferences.Editor editor = mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE).edit();

        editor.remove(key);
        editor.commit();
    }

    @Override
    public void invalidateAll() {
        SharedPreferences sp = mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        for (String key : sp.getAll().keySet()) {
            editor.remove(key);
        }

        editor.commit();
    }

    private boolean contains(String key) {
        return mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE).contains(key);
    }
}
