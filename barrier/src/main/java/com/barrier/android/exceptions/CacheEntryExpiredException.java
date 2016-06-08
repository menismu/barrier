package com.barrier.android.exceptions;

public class CacheEntryExpiredException extends Exception {

    public CacheEntryExpiredException() {
        super();
    }

    public CacheEntryExpiredException(String message) {
        super(message);
    }
}
