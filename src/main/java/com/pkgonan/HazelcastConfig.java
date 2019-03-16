package com.pkgonan;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactory;
import com.hazelcast.spi.properties.GroupProperty;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("hazelcast")
@Configuration
public class HazelcastConfig {

    @Bean
    public Config config() {
        Config config = new Config()
                .setProperty(GroupProperty.DISCOVERY_SPI_ENABLED.getName(), "true")
                .setInstanceName("cache-test");

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return HazelcastInstanceFactory.newHazelcastInstance(config());
    }

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}
