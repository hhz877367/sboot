package jvm.openjdk;

import org.openjdk.jol.info.ClassLayout;
public class TestOpenJDK {
    private  static int count = 0;
    public static void main(String[] args) {
     /*   try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //测试object
  /*      Object o = new Object();
        TestOpenJDK testOpenJDK = new TestOpenJDK();
        //测试锁对象
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();*/
        //测试锁类对象
   /*     System.out.println(ClassLayout.parseInstance(TestOpenJDK.class).toPrintable());
        new Thread(()->{
            synchronized (TestOpenJDK.class){
                System.out.println(ClassLayout.parseInstance(TestOpenJDK.class).toPrintable());
            }
        }).start();*/
       //测试升级为重量级所
        TestOpenJDK testOpenJDK = new TestOpenJDK();
        System.out.println(ClassLayout.parseInstance(testOpenJDK).toPrintable());
            new Thread(()->{
                synchronized (testOpenJDK){
                    count++;
                    System.out.println(Thread.currentThread().getName()+"----"+ClassLayout.parseInstance(testOpenJDK).toPrintable());
                }
            }).start();
        try {
            Thread.sleep(1000);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         new Thread(()->{
                synchronized (testOpenJDK){
                    count++;
                    System.out.println(Thread.currentThread().getName()+"----"+ClassLayout.parseInstance(testOpenJDK).toPrintable());
                }
            }).start();
        try {
            Thread.sleep(1000);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (testOpenJDK){
                count++;
                System.out.println(Thread.currentThread().getName()+"----"+ClassLayout.parseInstance(testOpenJDK).toPrintable());
            }
        }).start();
        try {
            Thread.sleep(1000);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
