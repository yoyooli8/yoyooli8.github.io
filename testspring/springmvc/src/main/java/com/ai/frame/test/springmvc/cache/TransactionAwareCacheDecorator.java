package com.ai.frame.test.springmvc.cache;

import org.springframework.cache.Cache;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

public class TransactionAwareCacheDecorator implements Cache{
    private final Cache targetCache;
    public TransactionAwareCacheDecorator(Cache targetCache) {
        Assert.notNull(targetCache, "Target Cache must not be null");
        this.targetCache = targetCache;
    }
    @Override
    public String getName() {
        return this.targetCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return this.targetCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        return this.targetCache.get(key);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return this.targetCache.get(key, type);
    }

    @Override
    public void put(final Object key, final Object value) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    targetCache.put(key, value);
                }
            });
        }
        else {
            this.targetCache.put(key, value);
        }
    }

    @Override
    public void evict(final Object key) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    targetCache.evict(key);
                }
            });
        }
        else {
            this.targetCache.evict(key);
        }
    }

    @Override
    public void clear() {
        this.targetCache.clear();
    }
}
