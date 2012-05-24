package com.bizintelapps.zytoon.service.util;

import com.bizintelapps.zytoon.domain.PasswordResetInfo;
import java.util.Date;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author intesar
 */
@Component
public class AccessCodeCacheImpl implements AccessCodeCache {

    private Cache cache;
    private int MAX_LIMIT = 5;
    private static final Logger logger = Logger.getLogger(AccessCodeCacheImpl.class);

    public AccessCodeCacheImpl() {

        //Create a CacheManager using defaults
        CacheManager manager = CacheManager.create();

        //Create a Cache specifying its configuration.
        cache = manager.getCache("com.bizintelapps.zytoon.domain.PasswordResetInfo");
    }

    @Override
    public void add(String email, String accessCode) {
        PasswordResetInfo info = new PasswordResetInfo(email, accessCode, new Date(), 0);
        Element element = new Element(email, info);
        cache.put(element);
    }

    @Override
    public boolean isValid(String email, String accessCode) {
        boolean isValid = false;
        Element element = cache.get(email);
        if (logger.isTraceEnabled()) {
            logger.trace("search element");
        }
        if (element != null) {
            if (logger.isTraceEnabled()) {
                logger.trace("element not null");
            }
            PasswordResetInfo info = (PasswordResetInfo) element.getValue();
            if (logger.isTraceEnabled()) {
                logger.trace("info " + info);
            }
            if (info.getAttempts() < MAX_LIMIT) {
                if (logger.isTraceEnabled()) {
                    logger.trace("within limits ");
                }
                if (info.getAccessCode().equals(accessCode)) {
                    isValid = true;
                } else {
                    if (logger.isTraceEnabled()) {
                        logger.trace("invalid codes " + accessCode);
                    }
                    info.setAttempts(info.getAttempts() + 1);
                }
            }
        }
        return isValid;
    }
}
