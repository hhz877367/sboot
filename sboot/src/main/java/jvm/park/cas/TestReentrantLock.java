package jvm.park.cas;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private  static int sum=0;
    public static void main(String[] args) {
        // lock  lock.lock();,最后解锁
         ReentrantLock lock = new ReentrantLock();
        for(int i=0;i<100;i++){
                Thread thread = new Thread(() -> {
                    lock.lock();
                    try {
                        for(int j=0;j<1000;j++){
                            sum++;
                        }
                    }catch (Exception e){

                    }finally {
                        lock.unlock();
                    }
                });
                thread.start();
            }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
