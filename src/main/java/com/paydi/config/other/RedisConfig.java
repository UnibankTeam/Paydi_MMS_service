package com.paydi.config.other;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import io.lettuce.core.ReadFrom;

@Configuration
public class RedisConfig {

    @Autowired
    private ClusterConfigurationProperties clusterProperties;

    @Bean
    LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisConfiguration) {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED).build();

        return new LettuceConnectionFactory(redisConfiguration, clientConfig);
    }

    @Bean
    RedisClusterConfiguration redisConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(
                clusterProperties.getNodes());
        redisClusterConfiguration.setMaxRedirects(clusterProperties.getMaxRedirects());

        return redisClusterConfiguration;
    }

    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // tạo ra một RedisTemplate
        // Với Key là Object
        // Value là Object
        // RedisTemplate giúp chúng ta thao tác với Redis
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}