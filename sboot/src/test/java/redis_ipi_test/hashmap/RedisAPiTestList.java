package redis_ipi_test.hashmap;

import com.baizhi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis_ipi_test.redi_model.Official;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        CreateHashMap();
        HashOperations opsHash = redisTemplate.opsForHash();
        Object o = opsHash.get("hash1", "zs");
        System.out.println(o.toString());

        //entries ，返回hash1 对应的所有key 和value
        Map hash1 = opsHash.entries("hash1");
        Set<Map.Entry<String, String>> set = hash1.entrySet();
        for (Map.Entry<String, String> o1 : set) {
            System.out.println("key="+o1.getKey()+"--value="+o1.getValue());

        }

        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("hash1", ScanOptions.scanOptions().match("zs").build());
        //Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("hashValue",ScanOptions.NONE);
        while (cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }


    }

    //创建Hashmap
    private void CreateHashMap(){
        HashOperations opsHash = redisTemplate.opsForHash();

        opsHash.put("hash1","zs","11");
        opsHash.put("hash1","zs1","22");
        opsHash.put("hash1","zs2","33");
        opsHash.put("hash1","zs3","44");

        opsHash.put("hash2","ls1","44");
        opsHash.put("hash2","ls2","33");
        opsHash.put("hash2","ls3","22");
        opsHash.put("hash2","ls4","11");
    }








}
