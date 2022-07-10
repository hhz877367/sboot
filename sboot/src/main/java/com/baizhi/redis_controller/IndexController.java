package com.baizhi.redis_controller;

import com.baizhi.constant.AjaxResult;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/indexController")
public class IndexController {
    @Resource
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @Resource
    private  RBloomFilter<String> rBloomFilter;
    //测试布隆过滤器能否使用
    @GetMapping("/testBloomFilter")
    public AjaxResult testBloomFilter() {
        System.out.println(rBloomFilter);
        System.out.println(rBloomFilter.contains("list1"));
        System.out.println(rBloomFilter.contains("stock"));
        System.out.println(rBloomFilter.contains("zs1"));
        return  AjaxResult.success("测试布隆过滤器");
    }





    @GetMapping("/deductStockIFAbsent")
    public AjaxResult deductStockIFAbsent() {
        String lockKey = "lock:product_101";
        //Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "zhuge");
        String clientId = UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
        if (!result) {
            System.out.println("系统繁忙，请稍后再试");
            return  AjaxResult.success("系统繁忙，请稍后再试");
        }
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return  AjaxResult.success("下单成功");
    }


    @GetMapping("/deductStock")
    public void deductStock() {
        String lockKey = "lock:product_101";
        //获取锁对象
        RLock redissonLock = redisson.getLock(lockKey);
        //加分布式锁
       redissonLock.lock();
        try {
            System.out.println("工作了"+"秒---------------");
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            redissonLock.unlock();
        }

    }

}