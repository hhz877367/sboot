package redis_ipi_test.list;

import com.baizhi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis_ipi_test.redi_model.Official;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisAPiTestList {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testString() {

    }




    @Test
    public void testSet() {
        ListOperations optList = redisTemplate.opsForList();
        optList.rightPush("list_zs_1","111");
        optList.rightPush("list_zs_1","222");
        optList.rightPush("list_zs_1","333");
        List list_zs_1 = optList.range("list_zs_1", 0, -1);
        for (Object o : list_zs_1) {
            System.out.println(o.toString());
        }

    }








    // 创建 A 关注了 10个微信好友模型
    @Test
    public void crearUserArticleList() {
        String userid="100";
        ListOperations optList = redisTemplate.opsForList();
        //开启10个线程，模拟10个好友发朋友圈
        for(int i=0;i<10;i++){
            new Thread(()->{
                //每个朋友发3个朋友圈
                for(int j=0;j<3;j++){
                    Official official = new Official();
                    official.setName(Thread.currentThread().getName());
                    official.setContent("内容"+j);
                    official.setId(Thread.currentThread().getId());
                    optList.leftPush("friends_"+userid,official);
                }
                System.out.println(Thread.currentThread().getName()+"---发送朋友圈成功");
            }).start();
        }
    }

    // 测试A打开朋友圈
    @Test
    public void openArticleList() {
        String userid="100";
        ListOperations optList = redisTemplate.opsForList();
        //开启10个线程，模拟10个好友发朋友圈
        List <Official> list = (List <Official>)optList.range("friends_" + userid, 0, -1);
        for (Official official : list) {
            System.out.println(official);
        }

    }
}
