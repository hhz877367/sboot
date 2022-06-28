package jvm.concurrentHashMap;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class TestList {
    @SneakyThrows
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for(int i=0;i<100;i++){
            Thread thread = new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name);
                for (int j = 0; j < 1000; j++) {
                    map.put("zs" + j, "lis" + j);
                }
            });
            thread.start();
            thread.join();
        }
        Thread.sleep(2000);
        System.out.println(map.size());



     /*   HashSet<String> set = new HashSet<String>();
        set.add(null);
        set.add(null);
        set.add(null);
        System.out.println(set.size());

        //
        HashMap<String, String> map = new HashMap<>();
        String put = map.put("zs", "ls");
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        Object put1 = concurrentHashMap.put("zs", "ls");*/

    }
}
