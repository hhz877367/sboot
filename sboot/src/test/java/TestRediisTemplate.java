import com.baizhi.Application;
import com.baizhi.entity_train.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRediisTemplate {
    @Resource
    RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testList() {
        ArrayList<Video> videoList = new ArrayList<>();
        Video video = new Video();
        video.setMes("v1");
        video.setType("t2");
        video.setUrl("u3");
        Video video2 = new Video();
        video2.setMes("v1");
        video2.setType("t2");
        video2.setUrl("u3");
        videoList.add(video2);
        redisTemplate.opsForList().leftPushAll("list1", videoList);
        System.out.println("设置redisc成功");
        System.out.println(redisTemplate);
    }

    @Test
    public void testThreadSetRedis() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().set("zs1", i);
        }
    }

    @Test
    public void testThreadGetRedis() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                Object zs1 = stringRedisTemplate.opsForValue().get("zs1");
                System.out.println(zs1.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testSetStock() {
            try {
                Thread.sleep(1000);
               stringRedisTemplate.opsForValue().set("stock","1600");
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }


}
