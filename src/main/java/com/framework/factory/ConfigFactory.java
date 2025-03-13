package com.framework.factory;

import com.framework.config.FrameworkConfig;
import org.aeonbits.owner.ConfigCache;

public class ConfigFactory {
    private ConfigFactory() {
    }

    public static FrameworkConfig getConfig() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}
