package com.baizhi.redis;

import com.baizhi.constant.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;

public class MybatisRedisCache implements Cache {
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private final String id;

  private static final RedisProperties PROPERTIES = SpringUtils.getBean(RedisProperties.class);

  private static final RedisTemplate TEMPLATE = SpringUtils.getBean(RedisTemplate.class);


  public MybatisRedisCache(final String id) {

    if (StringUtils.isBlank(id)) {
      throw new IllegalArgumentException("Cache instances require an ID");
    }

    this.id = id;
  }

  @Resource
  RedisProperties redisProperties;

  @PostConstruct
  public void init() {
  }

  /**
   * @return The identifier of this cache
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * @param key   The key
   * @param value
   */
  @Override
  public void putObject(Object key, Object value) {
    putObject(key, value, PROPERTIES.getExpireTime(), TimeUnit.SECONDS);
  }

  /**
   * @param key
   * @param value
   * @param expireTime
   * @param unit
   */
  public void putObject(Object key, Object value, int expireTime, TimeUnit unit) {
    if (Objects.nonNull(value)) {
      TEMPLATE.opsForValue().set(key, value, expireTime, unit);
    }
  }

  /**
   * @param key The key
   * @return The object stored in the cache.
   */
  @Override
  public Object getObject(Object key) {
    try {
      if (key != null) {
        return TEMPLATE.opsForValue().get(key);
      }
    } catch (Exception e) {
    }

    return null;
  }

  /**
   * As of 3.3.0 this method is only called during a rollback
   * for any previous value that was missing in the cache.
   * This lets any blocking cache to release the lock that
   * may have previously put on the key.
   * A blocking cache puts a lock when a value is null
   * and releases it when the value is back again.
   * This way other threads will wait for the value to be
   * available instead of hitting the database.
   * @param key The key
   * @return Not used
   */
  @Override
  public Object removeObject(Object key) {
    if (key != null) {
      TEMPLATE.delete(key);
    }

    return null;
  }

  /**
   * Clears this cache instance.
   */
  @Override
  public void clear() {
    Set<String> keys = TEMPLATE.keys("*:" + this.id + "*");
    if (!CollectionUtils.isEmpty(keys)) {
      TEMPLATE.delete(keys);
    }
  }

  /**
   * Optional. This method is not called by the core.
   * @return The number of elements stored in the cache (not its capacity).
   */
  @Override
  public int getSize() {
    Long size = (Long) TEMPLATE.execute(RedisServerCommands::dbSize);
    return size.intValue();
  }

  /**
   * Optional. As of 3.2.6 this method is no longer called by the core.
   * <p>
   * Any locking needed by the cache must be provided internally by the cache provider.
   * @return A ReadWriteLock
   */
  @Override
  public ReadWriteLock getReadWriteLock() {
    return this.readWriteLock;
  }

  public boolean hasKey(Object key) {
    if (key == null) {
      return false;
    }

    return TEMPLATE.hasKey(key);
  }

  /**
   * 获得缓存的list对象
   * @param key 缓存的键值
   * @return 缓存键值对应的数据
   */
  public <T> List<T> getCacheList(String key) {
    return TEMPLATE.opsForList().range(key, 0, -1);
  }

  /**
   * 缓存List数据
   * @param key      缓存的键值
   * @param dataList 待缓存的List数据
   * @return 缓存的对象
   */
  public <T> ListOperations<String, T> putCacheList(String key, List<T> dataList) {
    ListOperations listOperation = TEMPLATE.opsForList();
    if (dataList == null) {
      return null;
    }

    dataList.parallelStream().forEach(e -> listOperation.leftPush(key, e));

    return listOperation;
  }

  /**
   * 获得缓存的基本对象列表
   * @param pattern 字符串前缀
   * @return 对象列表
   */
  public Collection<String> keys(String pattern) {
    return TEMPLATE.keys(pattern);
  }

  public HashOperations<String, String, Object> hashOperations() {
    return TEMPLATE.opsForHash();
  }

  public ValueOperations<String, String> valueOperations() {
    return TEMPLATE.opsForValue();
  }

  public ListOperations<String, Object> listOperations() {
    return TEMPLATE.opsForList();
  }

  public SetOperations<String, Object> setOperations() {
    return TEMPLATE.opsForSet();
  }

  public ZSetOperations<String, Object> zSetOperations() {
    return TEMPLATE.opsForZSet();
  }
}
