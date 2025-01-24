package 并发编程.synchronizedtest;

/**
*  普通方法 普通对象，多线程，多例情况下原子操作被打破，单例不会被打破
* */
public class synchronizedLockObject {
    public static void main(String[] args) {
        //测试 synchronized 修饰普通方法
        Work person1 = new Work();
        Work person2 = new Work();  // 修改

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("t1开始");
                person1.work("t1线程老板 叫person1 工作啦");
                System.out.println("t1结束");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("t2开始");
                person2.work("t2线程老板 叫person2 工作啦"); // 修改
                System.out.println("t2结束");
            }
        };
        t1.start();
        t2.start();

    }
}
