package 并发编程.aqs_countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 让单个线程等待：多个线程(任务)完成后，进行汇总合并
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " finish task" + index);

                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //其他线程也可阻塞
        for(int j=0;j<10;j++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    System.out.println("其他线程可执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("");
            }).start();
        }

        // 主线程在阻塞，当计数器==0，就唤醒主线程往下执行。
        countDownLatch.await();



        long count = countDownLatch.getCount();
        System.out.println(count);
        System.out.println("主线程:在所有任务运行完成后，进行结果汇总");

    }
}

