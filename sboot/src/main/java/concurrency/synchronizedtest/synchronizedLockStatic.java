package concurrency.synchronizedtest;
/**
 *  静态方法 类洗东西，多线程下，多例，单例原子操作都不会被打破
 * */
public class synchronizedLockStatic {
        public static void main(String[] args) {
            //测试 synchronized 修饰普通方法

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    System.out.println("t1开始");
                    Work.work2("t1线程老板 叫person1 工作啦");
                    System.out.println("t1结束");
                }
            };

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    System.out.println("t2开始");
                    Work.work2("t2线程老板 叫person2 工作啦"); // 修改
                    System.out.println("t2结束");
                }
            };
            t1.start();
            t2.start();

        }
}
