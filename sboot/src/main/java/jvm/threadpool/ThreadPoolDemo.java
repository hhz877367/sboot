package jvm.threadpool;


import lombok.Data;
import sun.nio.ch.ThreadPool;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
     /*   ExecutorService executorService1 = Executors.newCachedThreadPool();//快
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);//慢
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();//最慢*/
        ThreadPoolExecutor excutor = new ThreadPoolExecutor(10,20,
                0L,TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(10));
        for(int i=1;i<=100;i++){
            try {
                MyTask myTask = new MyTask(i);
                myTask.interrupt();
                excutor.execute(myTask);
            }catch (Exception e){
                break;
            }

        }

        excutor.shutdown();
        System.out.println("任务完成");
    }
}

/***
 * 项目
 */
@Data
class MyTask extends Thread {
    int i = 0;
    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            Thread.interrupted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "程序员做第完" + i + "个项目");
    }
}
