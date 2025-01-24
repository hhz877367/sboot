package redis_ipi_test.string;

import com.baizhi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisAPiTest {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testString() {
        ValueOperations opt = redisTemplate.opsForValue();
        opt.set("zs","zsvalue");
        opt.set("zs1","zsvalue1");
        opt.set("zs3",1);
        Boolean aBoolean = opt.setIfPresent("zs1", "zsvalue2");
        System.out.println("setIfAbsent----"+aBoolean);
        System.out.println(opt.get("zs1").toString());
        System.out.println(redisTemplate.hasKey("zs"));

        Long zs3 = opt.increment("zs3");
        System.out.println("opt.increment----"+zs3);
        System.out.println("opt_increment_long"+opt.increment("zs3",10L));
        ArrayList<String> listKey = new ArrayList<>();
        listKey.add("zs");
        redisTemplate.delete(listKey);
        System.out.println(redisTemplate.hasKey("zs"));
    }

}
