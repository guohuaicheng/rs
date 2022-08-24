//package com.qiqi.rs.admin.core.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.cache.CacheProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//
//@Configuration
//public class RedisCacheConfig {
//
//    @Bean
//    public CacheProperties cacheProperties() {
//        return new CacheProperties();
//    }
//
//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//
////    @Bean
////    public RedisTemplate redisTemplate() {
////        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
////        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//////        ObjectMapper om = new ObjectMapper();
//////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//////        jackson2JsonRedisSerializer.setObjectMapper(om);
////        RedisTemplate redisTemplate = new RedisTemplate();
////        redisTemplate.setConnectionFactory(this.jedisConnectionFactory);
////        redisTemplate.setHashKeySerializer(stringRedisSerializer);
////        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
////        redisTemplate.setKeySerializer(stringRedisSerializer);
////        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
////        redisTemplate.afterPropertiesSet();
////        return redisTemplate;
////    }
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//
//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//
////        ObjectMapper om = new ObjectMapper();
////        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        //redis默认配置文件,并且设置过期时间
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).serializeValuesWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
//        );
//
//        CacheProperties.Redis redisProperties = cacheProperties().getRedis();
//
//        if (redisProperties.getTimeToLive() != null) {
//            config = config.entryTtl(redisProperties.getTimeToLive());
//        }
//
//        if (redisProperties.getKeyPrefix() != null) {
//            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
//        }
//
//        if (!redisProperties.isCacheNullValues()) {
//            config = config.disableCachingNullValues();
//        }
//
//        if (!redisProperties.isUseKeyPrefix()) {
//            config = config.disableKeyPrefix();
//        }
//
//        //RedisCacheManager 生成器创建
//        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
//    }
//
//}
//
////class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
////
////    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
////
////    private Class<T> clazz;
////
////    public FastJsonRedisSerializer(Class<T> clazz) {
////        super();
////        this.clazz = clazz;
////    }
////
////    @Override
////    public byte[] serialize(T t) throws SerializationException {
////        if (t == null) {
////            return new byte[0];
////        }
////        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
////    }
////
////    @Override
////    public T deserialize(byte[] bytes) throws SerializationException {
////        if (bytes == null || bytes.length <= 0) {
////            return null;
////        }
////        String str = new String(bytes, DEFAULT_CHARSET);
////        return (T) JSON.parseObject(str, clazz);
////    }
////
////}