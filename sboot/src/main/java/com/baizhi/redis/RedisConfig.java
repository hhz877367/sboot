package com.baizhi.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import java.time.Duration;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
@EnableCaching(mode = AdviceMode.PROXY)
public class RedisConfig extends CachingConfigurerSupport {
  @Resource
  private RedisConnectionFactory redisConnectionFactory;

  @Resource
  private RedisProperties redisProperties;

  @Resource
  private GmzProperties gmzProperties;

  @Override
  @Bean(name = "cacheKeyGenerator")
  @Primary
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> CacheHashCode.of(params);
  }

  @Bean
  public MybatisRedisCache mybatisRedisCache() {
    String module = gmzProperties.getModule();
    return new MybatisRedisCache(module);
  }

  @Bean
  public ConversionService cacheKeyConversionService() {
    return new CacheKeyConversionService();
  }

  /**
   * spring boot 缓存默认配置
   *
   * @return
   */
  @Override
  @Bean
  public CacheManager cacheManager() {
    return RedisCacheManager.builder(redisConnectionFactory)
        //默认缓存时间
        .cacheDefaults(getRedisCacheConfigurationWithTtl(60))
        .transactionAware()
        .withInitialCacheConfigurations(getRedisCacheConfigurationMap())
        //自定义缓存时间
        .build();
  }

  /**
   * 自定义缓存时间
   *
   * @return
   */
  private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
    Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = Maps.newHashMap();
    redisCacheConfigurationMap.put("test", this.getRedisCacheConfigurationWithTtl(
        redisProperties.getExpireTime()));
    return redisCacheConfigurationMap;
  }

  private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
    return RedisCacheConfiguration.defaultCacheConfig().entryTtl(
        //定义默认的cache time-to-live.(缓存存储有效时间)
        Duration.ofSeconds(seconds))
        //静止缓存为空
        .disableCachingNullValues()
        //此处定义了cache key的前缀, 避免公司不同项目之间的key名称冲突.
        .computePrefixWith(
            cacheName -> gmzProperties.getModule().concat(":")
                .concat(cacheName).concat(":"))
        //定义key和value的序列化协议, 同时的hash key和hash value也被定义.
        .serializeKeysWith(RedisSerializationContext.SerializationPair
            .fromSerializer(
                new StringRedisSerializer()))
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair
                .fromSerializer(createJackson2JsonRedisSerializer()))
        //自定义key的生成策略, 将方法参数转换为hashcode, 作为redis key. 需要做两个事情,
        // 一个是添加一个自定义的ConversionService, 另一个是需要自定义一个KeyGenerator.
        .withConversionService(cacheKeyConversionService());
  }

  @Bean
  @Primary
  @ConditionalOnMissingBean(name = "redisTemplate")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    //创建连接工厂
    template.setConnectionFactory(connectionFactory);

    // 使用 StringRedisSerializer 来序列化和反序列化redis的key值
    template.setKeySerializer(new StringRedisSerializer());
    //默认使用
    FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    serializer.setObjectMapper(mapper);
    template.setValueSerializer(serializer);
    template.setHashKeySerializer(serializer);
    template.setHashValueSerializer(serializer);
    //设置redis支持数据库的事务
//        template.setEnableTransactionSupport(true);
    //初始化设置并且生效
    template.afterPropertiesSet();

    return template;
  }

  /**
   * 创建redis序列化
   *
   * @return
   */
  private RedisSerializer<Object> createJackson2JsonRedisSerializer() {
    ObjectMapper objectMapper = new ObjectMapper();
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(
            Object.class);
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    //设置需要序列化的类必须是非Final的 否则抛异常
    objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    return jackson2JsonRedisSerializer;
  }
}