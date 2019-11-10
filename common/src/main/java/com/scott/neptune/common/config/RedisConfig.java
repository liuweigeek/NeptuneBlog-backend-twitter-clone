package com.scott.neptune.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/11/10 18:19
 * @Description: 缓存配置
 */
@Slf4j
@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        return RedisCacheManager.builder(lettuceConnectionFactory).build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //设置序列化工具
        Jackson2JsonRedisSerializer serializer = constructSerializer();
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }


    private Jackson2JsonRedisSerializer constructSerializer() {
        @SuppressWarnings({"rawtypes", "unchecked"})
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        om.setDateFormat(sdf);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @PostConstruct
    public void afterInit() {
        log.info("Config Redis Cache");
    }

}
