package question;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
*<p>背景：消息队列用于异步处理消息，其底层为生产者-消费者模式。现在有一个生产果汁(Juice)的商店，把产生的果汁放在
* 吧台上，当吧台摆满时不再生产；消费者从吧台购买果汁，当吧台上果汁卖光时需要等待新生产的果汁上架。
*<p>使用多线程编程模拟该场景，
*<li>使用BlockingQueue模拟吧台，最多暂存10瓶果汁；
*<li>当吧台满时等商店最多等待5秒，超过后因生意不好提前打烊，另外该商店一天最多生产100瓶果汁，卖完后打烊；
*<li>当吧台为空时消费者需要等待，但每一个消费者最多等待1秒，超时后离开，下一个消费者重新等待，另外打烊后消费者都不再等待了；
*<li>商店生产一瓶果汁需要0.1秒，消费者购买过程因付费方式不同随机耗时0.05~0.2秒 50-200毫秒；
*
*<p>要求：题目已提供生产者的demo，在此基础上改进和扩展（修改生产时间、超时时间等），并完成消费者模型的模拟，
*       分别实现下面3种场景:
*<li>生意不好提前打烊
*<li>卖完100瓶后打烊
*<li>至少有1名消费者等待超时
 */
public class Question2 {

    private   class Juice {
        //...
    }

    private   BlockingQueue<Juice> queue = new LinkedBlockingQueue<>(10);
    /**
     * 打烊标记
     */
    private volatile boolean close = false;

    private volatile int outTime = 0;
    private void runProducer() {
        close = false;
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        boolean b = queue.offer(new Juice(), 5, TimeUnit.SECONDS);
                        if (!b) {
                            System.err.println("生意不好提前打烊");
                            close = true;
                            return;
                        } else {
                            System.err.println("生产了1瓶，现有" + queue.size() + "瓶在吧台");
                        }
                    } catch (InterruptedException e) {
                    }
                }
                while (!close && !queue.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                System.err.println("已全部卖完，打烊！");
                close = true;
            };
        };
        producer.start();
    }

    private void simulateEarlyClose() {
        Thread consumers = new Thread() {
            public void run() {
                //TODO
                while (close){
                    try {
                        Thread.sleep(new Random().nextInt(150)+50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Juice poll = queue.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        try {
            Thread.sleep(500);
            consumers.start();
            consumers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simulateSoldOut() {
        Thread consumers = new Thread() {
            @Override
            public void run() {
                //TODO
                while (close){
                    try {
                        Thread.sleep(new Random().nextInt(150)+50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Juice poll = queue.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            };
        };
        try {
            Thread.sleep(500);
            consumers.start();
            consumers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simulateWaitingTimeOut() {
        Thread consumers = new Thread() {
            @Override
            public void run() {
                //TODO
                while (close){
                    try {
                        Thread.sleep(new Random().nextInt(150)+50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Juice poll = queue.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        try {
            Thread.sleep(500);
            consumers.start();
            consumers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================");
        Question2 instance = new Question2();
        instance.runProducer();
        instance.simulateEarlyClose();

        System.out.println("==============================");
        Question2 instance2 = new Question2();
        instance2.runProducer();
        instance2.simulateSoldOut();

        System.out.println("==============================");
        Question2 instance3 = new Question2();
        instance3.runProducer();
        instance3.simulateWaitingTimeOut();
    }
}
