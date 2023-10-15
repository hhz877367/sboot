package 并发编程.aqs_reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

// 可中断 通过 t1.interrupt()和  lock.lockInterruptibly()、 lock.unlock()的配合，能够做到线程A控制线程B的等待中断;
public class interruptiblyReentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            System.out.println("t1启动...");

            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("t1获得了锁");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1等锁的过程中被中断");
            }

        }, "t1");

        lock.lock();
        try {
            System.out.println("main线程获得了锁");
            t1.start();
            //先让线程t1执行
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.interrupt();
            System.out.println("线程t1执行中断");
        } finally {
            lock.unlock();
        }
    }
}
