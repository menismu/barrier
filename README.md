# Barrier. A lightweight API and offline cache for Android.

### 1. Overview

Barrier is a lightweight cache library to use in your Android projects or applicaitons to cache data received from APIs consumed, although it can be used to store other data in the cache.

It has been developed with the main idea of two different cache type, API cache and offline cache after being part of several applications after a long period of time. API cache is commonly used to store the API responses to request the same data again later and don't consume more bandwidth, and the cache entries expire after a configurable perior of time. In the other hand, offline is used to store data needed also when the Android device is offline (no network).

It uses as default [Shared Preferences] (https://developer.android.com/training/basics/data-storage/shared-preferences.html) to store the data inside of Android devices, but it can be extended to other approaches.

### 2. Using API cache

To store or save a JSON data replied by an API call, only is needed to follow the lines below:

```
// As an example it is used a hardcoded JSON string, in the real world it would be a json string returned by an API call
String jsonString = "{ key: \"value\" }";
APICache apiCache = new APICache(mContext);
apiCache.put("dataCacheKey", dataString);
```
Later, the same data can be retrieved using the get method of the cache:

```
try {
  String jsonString = apiCache.get("dataCacheKey");
} catch (CacheEntryNotFoundException entryNotFoundException) {
  // entry does not exist in the cache
} catch (CacheEntryExpiredException entryExpiredException) {
  // entry expired, not longer valuid and should be retrieved again to the API
}
```

### 3. Using Offline cache

Offline cache implementation works in the same way than API Cache, to save or put data into the cache:

```
// As an example it is used a hardcoded JSON string, in the real world it would be a json string returned by an API call
String jsonString = "{ key: \"value\" }";
OfflineCache offlineCache = new OfflineCache(mContext);
offlineCache.put("dataCacheKey", dataString);
```
And to get the data saved:

```
try {
  String jsonString = offlineCache.get("dataCacheKey");
} catch (CacheEntryNotFoundException entryNotFoundException) {
  // entry does not exist in the cache
} catch (CacheEntryExpiredException entryExpiredException) {
  // entry expired, in offline cache shouldn't happen if the default invalidator is used
}
```

### 4. Other operations

Cache implementations also let to check if a particular data key already exists in the cache as follows:

```
AIPCache cache = new APICache(mContext);
String dataKey = "dataCacheKey";
if (cache.contains(dataKey)) {
   String data = cache.get(dataKey);
}
```

The other two operations available let the cache entries to be invalidated, to invalidate a one single cache entry:

```
// assuming the cache contains an entry with a key "dataCacheKey"
String dataKey = "dataCacheKey";
cache.invalidate(dataKey);

// cache entry with key "dataCacheKey" is not longer available, cache.get will throw CacheEntryNotFoundException
```

And to invalidate all the entries in the cache:

```
cache.invalidateAll();
```

### 5. Import Barrier to your project

Right now, Barrier can be imported in your Android projects by two different ways, importing an aar file or importing as a module. Fro both ways the source code should be checked out and build in Android Studio to produce an aar output file or copying the code in the folder barrier to your Android project and adding to gradle.settings the new module name.

### 6. What's next

There are two main areas right now for this library, one is related to packages to be published and the other one is unit tests to be written. This library is opened to collaborators who want to add new stuff to the library as well as use or test the libraries in other projects.
