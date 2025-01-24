package redis_ipi_test.zset;

import com.baizhi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisZsetList {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testRank(){
        ZSetOperations zseOpt = redisTemplate.opsForZSet();
        //测试 redis 10万个key 排序效率
       /* long l = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            zseOpt.add("zsez_key_1",i+"zs",new Random().nextInt(1000000));
        }
        //取中位数

        Set<ZSetOperations.TypedTuple> zsez_key_1 = zseOpt.rangeByScoreWithScores("zsez_key_1", 0, 1000000);
        long l1 = System.currentTimeMillis();
        System.out.println("计算排序时间"+(l1-l));*/
        //只要有range----对应reverseRange---这里不在进行重复测试


      /*  Set zsez_key_1 = zseOpt.range("zsez_key_2", 0, -1);
        System.out.println("----测试range开始");
        for (Object o : zsez_key_1) {
            System.out.println(o.toString());
        }
        System.out.println("----测试range结束");

        Set zsez_key_2 = zseOpt.rangeByScore("zsez_key_2", 0, 1000000);
        System.out.println("----测试rangeByScore开始");
        for (Object o : zsez_key_2) {
            System.out.println(o.toString());
        }
        System.out.println("----测试rangeByScore结束");
        Set<ZSetOperations.TypedTuple> zsez_key_3 = zseOpt.rangeWithScores("zsez_key_2", 0, -1);
        System.out.println("----测试rangeWithScores开始");
        for (ZSetOperations.TypedTuple o : zsez_key_3) {
            System.out.println("value="+o.getValue()+"score="+o.getScore());
        }


        Set<ZSetOperations.TypedTuple> zsez_key_4= zseOpt.rangeByScoreWithScores("zsez_key_2", 0, 1000000);
        System.out.println("----测试rangeByScoreWithScores开始");
        for (ZSetOperations.TypedTuple o : zsez_key_4) {
            System.out.println("value="+o.getValue()+"score="+o.getScore());
        }
        System.out.println("----测试rangeByScoreWithScores结束");*/



    }
}
