package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever  {

    OriginalRetriever original;

    LRUCache cashe;

    public CachingProxyRetriever(Storage storage) {
        original = new OriginalRetriever(storage);
        cashe = new LRUCache(1000);
    }

    @Override
    public Object retrieve(long id) {
        Object obj = cashe.find(id);
        if (obj == null) {
            obj = original.retrieve(id);
            cashe.set(id, obj);
        }
        return obj;
    }
}
