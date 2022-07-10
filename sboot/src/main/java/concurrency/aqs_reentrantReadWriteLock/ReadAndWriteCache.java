package concurrency.aqs_reentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//自定义线程安全的hashMap

public class ReadAndWriteCache {

    public static void main(String[] args) {
        map.put("1","111");
        map.put("2","222");
        map.put("3","333");
        map.put("4","444");
        map.put("5","555");

        for(int i=0;i<10;i++){
            if(i%2==0){
                new Thread(()->{
                    r.lock();
                    Object o = map.get("1");
                    System.out.println(o.toString());
                    r.unlock();
                }).start();
            }else {
                new Thread(()->{
                    w.lock();
                    Object o = map.get("1");
                    System.out.println(o.toString());
                    w.unlock();
                }).start();
            }

        }
    }

    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 设置key对应的value，并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
