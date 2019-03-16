package com.pkgonan;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("infinispan")
@Configuration
public class InfinispanConfig {

    @Bean("infinispan")
    public EmbeddedCacheManager cacheManager() {

        GlobalConfigurationBuilder global = new GlobalConfigurationBuilder()
                .transport().defaultTransport()
                .addProperty("configurationFile", "default-configs/default-jgroups-udp.xml")
                .clusterName("some-cache-cluster")
                .defaultCacheName("default-cache");

        ConfigurationBuilder config = new ConfigurationBuilder();
        config.simpleCache(true);

        EmbeddedCacheManager cacheManager = new DefaultCacheManager(
                global.build(),
                config.build()
        );

        return cacheManager;
    }

    @Bean
    public Cache<String, String> invalidationCache(@Qualifier("infinispan") EmbeddedCacheManager cacheManager) {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.clustering().cacheMode(CacheMode.INVALIDATION_ASYNC);

        cacheManager.defineConfiguration("invalidation-cache", config.build());
        return cacheManager.getCache("invalidation-cache");
    }
}
