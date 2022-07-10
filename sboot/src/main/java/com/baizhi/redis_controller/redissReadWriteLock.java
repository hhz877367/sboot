package com.baizhi.redis_controller;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class redissReadWriteLock {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "lock:product_101";
        //获取锁对象
        RLock rLock = redisson.getReadWriteLock(lockKey).readLock();
        rLock.lock();
        try {
            System.out.println("进行业务处理");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }

        return "end";
    }


    @GetMapping("/redlock")
    public String redlock() {
        String lockKey = "product_001";
        //获取写所对象
        RLock rlock = redisson.getReadWriteLock(lockKey).writeLock();

        /**
         * 根据多个 RLock 对象构建 RedissonRedLock （最核心的差别就在这里）
         */
        rlock.lock();
        try {
            System.out.println("进行业务处理");
           /*
           * 进行业务处理
           *
           * */
        } catch (Exception e) {
            throw new RuntimeException("lock fail");
        } finally {
            //无论如何, 最后都要解锁
            rlock.unlock();
        }
        return "end";
    }
}
