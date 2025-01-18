package com.syndicate.cache.config;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.event.CacheEventListener;

public class PartyCacheEventLogger implements CacheEventListener {

    private static final Logger LOG= LoggerFactory.getLogger(PartyCacheEventLogger.class);
//    @Override
//    public void onEvent(CacheEvent cacheEvent) {
//        LOG.info("custom Caching event {} {} {} {} ", cacheEvent.getType(),cacheEvent.getKey(),cacheEvent.getOldValue(),cacheEvent.getNewValue());
//    }

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {

    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {

    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {

    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {

    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {

    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public void dispose() {

    }
}
