package 并发编程.synchronizedtest;

public class WaitAndNotifyTest {
    public static void main(String[] args) {

        Object object=new Object();
        Thread thread1=new Thread(()->{
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程1执行完成");
        });

        Thread thread2=new Thread(()->{
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程2执行完成");
        });

        Thread thread3=new Thread(()->{
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程3执行完成");
        });
        thread1.start();
        thread2.start();
        thread3.start();

        Thread thread4=new Thread(()->{
            synchronized (object){
                //使用一次notify只能唤醒一个线程；
                //使用一次notifyAll可唤醒所有的线程
               //object.notify();
                object.notifyAll();
                System.out.println("线程4去唤醒wait的线程");
            }

        });
        thread4.start();


    }
}
