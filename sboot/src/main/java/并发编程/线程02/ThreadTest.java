package 并发编程.线程02;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
         AtomicInteger count = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        Random random;
        random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i =0;i<10000;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 线程执行的代码逻辑
                    // ...
                    list.add(random.nextInt());
                };
            });
            thread.start();
            thread.join();
        }
        System.out.println("时间 :" + (System.currentTimeMillis() - start));
        System.out.println("大小 :" + list.size());
    }
}
