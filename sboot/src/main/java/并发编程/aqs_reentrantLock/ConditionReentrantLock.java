package 并发编程.aqs_reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cigCon = lock.newCondition();
    private static Condition takeCon = lock.newCondition();

    private static boolean hashcig = false;
    private static boolean hastakeout = false;

    //送烟
    public void cigratee(){
        lock.lock();
        try {
            while(!hashcig){
                try {
                    System.out.println("没有烟，歇一会");
                    cigCon.await();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("有烟了，干活");
        }finally {
            lock.unlock();
        }
    }

    //送外卖
    public void takeout(){
        lock.lock();
        try {
            while(!hastakeout){
                try {
                    System.out.println("没有饭，歇一会");
                    takeCon.await();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("有饭了，干活");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionReentrantLock test = new ConditionReentrantLock();
        new Thread(() ->{
            test.cigratee();
        }).start();

        new Thread(() -> {
            test.takeout();
        }).start();

        new Thread(() ->{
            lock.lock();
            try {
                hashcig = true;
                System.out.println("唤醒送烟的等待线程");
                cigCon.signal();
            }finally {
                lock.unlock();
            }


        },"t1").start();

        new Thread(() ->{
            lock.lock();
            try {
                hastakeout = true;
                System.out.println("唤醒送饭的等待线程");
                takeCon.signal();
            }finally {
                lock.unlock();
            }


        },"t2").start();
    }
}
