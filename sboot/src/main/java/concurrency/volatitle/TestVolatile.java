package concurrency.volatitle;

import sun.misc.Contended;
import sun.misc.Lock;

public class TestVolatile {
    @Contended //避免伪共享
    private Lock Student;
    volatile static int count=0;
    public static void main(String[] args) throws InterruptedException {
        // volatile 不生效原因，虽然触发了CPU的MESI协议，当一个线程修改了共享变量之后，还没刷回主存，另一个线程已经拿到值先进行操作了，
        // 导致另一个线程刷数据回主存失效,也就导致了其中的一个线程的运算失效。
        for(int i=0;i<10;i++){
            Thread thread = new Thread(() -> {
                for(int j=0;j<1000;j++){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
