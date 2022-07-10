package redis_ipi_test.set;

import com.baizhi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisAPiTestSet {
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



    //测试点赞
    @Test
    public void createLuck() {
        SetOperations setops = redisTemplate.opsForSet();
        Set<String> userIdList = new HashSet();
        setops.add("set1", "A");
        setops.add("set1", "B");
        setops.add("set1", "C");
        setops.add("set1", "D");
        setops.add("set1", "E");
        setops.add("set1", "F");
        setops.add("set1", "G");


        setops.add("set2", "A");
        setops.add("set2", "B");
        setops.add("set2", "C");


        setops.add("set3", "M");
        setops.add("set3", "N");
        setops.add("set3", "Q");
        setops.add("set3", "A");

        System.out.println(redisTemplate.opsForSet().difference("set1","set2").toString());
        System.out.println(redisTemplate.opsForSet().difference("set2","set1").toString());




        //查看所有抽奖用户
     /*   Set<String> userList = (Set<String>) setops.members("luck_lickid_100");
        System.out.println("--------------------testLuck开始");
        for (Object o : userList) {
            System.out.println(o.toString());
        }

        System.out.println("--------------------testLuck结束");
        //随机返回一个，并且不删除原队列中的数据
        Object luck_lickid_100 = setops.randomMember("luck_lickid_100");
        System.out.println(luck_lickid_100.toString());
        //随机返回N个，并且不删除原队列中的数据
        List luck_lickid_1001 = setops.randomMembers("luck_lickid_100", 3);
        System.out.println(luck_lickid_1001.toString());

        Set<String> userList2 = (Set<String>) setops.members("luck_lickid_100");
        System.out.println(userList2.size());
        for (Object o : userList2) {
            System.out.println(o.toString());
        }*/
        System.out.println("----测试随机删除");
    //    Object popOne = setops.pop("luck_lickid_100");
      //  System.out.println("随机移除一个popOne----"+popOne.toString());

        //setops.pop("luck_lickid_100", 2) 很奇怪，不知道为啥不能用，报错
        Set<String> luck_lickid_1001 = (Set<String>) setops.members("luck_lickid_100");
     /*   System.out.println(luck_lickid_1001.toString());
        List<String> luck_lickid_100 = (List<String>) setops.pop("luck_lickid_100", 2);
        //System.out.println(popOne.toString());
        System.out.println(luck_lickid_100.toString());*/
        //测试remove 方法
        //setops.remove("luck_lickid_100", "user100", "user102");
        // Scan方法
        Cursor cursor = setops.scan("set2", ScanOptions.scanOptions().match("A").build());

        System.out.println("测试scan开始");
        while (cursor.hasNext()){
            Object next = cursor.next();
            System.out.println("测试scan中"+next.toString());
        }
        System.out.println("测试scan结束");
        //测试集合差
        Set difference = setops.difference("set1", "set2");
        System.out.println("测试集合差-------");
        for (Object o : difference) {
            System.out.println(o.toString());
        }
        System.out.println("测试集合差-------");
        ArrayList<String> list = new ArrayList<>();
        list.add("set1");
        list.add("set2");
        System.out.println("测试集合差-------");
        Set difference2 = setops.difference(list);
        for (Object o : difference2) {
            System.out.println(o.toString());
        }
        System.out.println("测试集合差-------");

        System.out.println("测试集合合");
        List set = redisTemplate.opsForSet().pop("set3", 2);
        //获取key与另一个otherKey所对应的集合之间的差值，并将结果存入指定的destKey中
        redisTemplate.opsForSet().differenceAndStore("set1,","set2","set4");
        redisTemplate.opsForSet().intersectAndStore("set2","set3","set5");
        redisTemplate.opsForSet().unionAndStore("set2","set3","set6");





        Set<String> userList3 = (Set<String>) setops.members("luck_lickid_100");
        System.out.println(userList3.size()+"---------------");
        for (Object o : userList3) {
            System.out.println(o.toString());
        }


    }









    @Test
    public void crearUserArticleList() {
        String userid="100";
        SetOperations redisSet = redisTemplate.opsForSet();
        redisSet.add("aticle_user"+100,"");

    }
}
