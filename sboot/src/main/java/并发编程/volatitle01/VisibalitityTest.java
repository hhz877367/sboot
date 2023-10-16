package 并发编程.volatitle01;

//volatile  包装了线程的共享变量的可见性
public class VisibalitityTest {
    //释放时间片，上下文切换，Thread.yield();
    //内存屏障 thread.sleep sout
    //volatile  底层是C++ 写的，使用了Lock指令(lock 指令会使CPU的MESI协议生效,有个64个字节的缓存行,和总线锁定机制。改变CPU对这个变量赋值的状态)， 使用volatile修饰的变量，写操作完成之后，立即刷回主内存，
    //并且其他线程读到此变量时，此变量的状态变为I无效状态，然后主动去主内存中拿保证执行的有序性，但不能保证执行的原子性。如果两个线程先后拿到此变量
    //但当此变量的状态变为I无效状态时，第二个拿到变量的线程已经操作完对此变量的修改，则会导致修改无效，不往主内存区里刷数据。
    //其中一个线程拿到变量
    private  /*volatile*/   boolean flag = true;
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {

        VisibalitityTest test = new VisibalitityTest();
        Thread threadA = new Thread(() -> test.load(), "threadA");
        threadA.start();
        //睡眠1秒钟
        Thread.sleep(1000);
        Thread threadB = new Thread(() -> test.refresh(), "threadB");
        threadB.start();

    }

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改当前线程 flag=" + flag);
    }

    public void load() {
        while (flag) {
            //Thread.yield();
            System.out.println("aaa");
            count++;
        }
        System.out.println(Thread.currentThread().getName() + "线程A跳出循环 count=" + count);
        System.out.println(Thread.currentThread().getName() + "线程A跳出循环 flag=" + flag);
    }

}
