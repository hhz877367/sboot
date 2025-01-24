package com.baizhi.redisson_bloomfilter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;


@Component
public class RedissonBloomFilter {
    @Resource
    RedisTemplate redisTemplate;

    @Bean
    public  RBloomFilter<String>  rBloomFilter(){
        System.out.println(redisTemplate);
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        //构造Redisson
        Object nameList = redisTemplate.opsForValue().get("nameList");
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("nameList");
        boolean b = bloomFilter.tryInit(100000L, 0.03);
        if(b){
            //初始化布隆过滤器：预计元素为100000000L,误差率为3%,根据这两个参数会计算出底层的bit数组大小
            Set<String> keys = redisTemplate.keys("*");
            for (String key : keys) {
                bloomFilter.add(key);
            }
        }
        return bloomFilter;
    }

}
