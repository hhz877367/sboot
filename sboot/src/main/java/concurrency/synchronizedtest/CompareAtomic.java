package concurrency.synchronizedtest;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Contended;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CompareAtomic {
    @Contended
    static AtomicInteger sum = new AtomicInteger(0);

    private int count;

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        CompareAtomic compareTestLock = new CompareAtomic();
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(() -> {
                try {
                    long l = System.currentTimeMillis();
                    System.out.println(ClassLayout.parseInstance(compareTestLock).toPrintable());
                    synchronized (compareTestLock) {
                        for (int j = 0; j < 1000000; j++) {
                            compareTestLock.count++;
                        }
                    }

                    long l1 = System.currentTimeMillis();
                    System.out.println("synchronized消耗时间" + (l1 - l));

                } catch (Exception e) {

                } finally {
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(compareTestLock.count);

  /*      for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    long l = System.currentTimeMillis();
                    for (int j = 0; j < 1000000; j++) {
                        sum.incrementAndGet();
                    }
                    long l1 = System.currentTimeMillis();
                    System.out.println("当前线程消耗时间" + (l1 - l));

                } catch (Exception e) {

                } finally {
                }
            });
            thread.start();
        }*/
    /*    for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    long l = System.currentTimeMillis();
                    lock.lock();
                    try {
                        for (int j = 0; j < 1000000; j++) {
                            sum.incrementAndGet();
                        }
                    } finally {
                        lock.unlock();
                    }
                    long l1 = System.currentTimeMillis();
                    System.out.println("当前线程消耗时间" + (l1 - l));

                } catch (Exception e) {

                } finally {
                }
            });
            thread.start();
        }*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Atomic" + sum);
    }
}
