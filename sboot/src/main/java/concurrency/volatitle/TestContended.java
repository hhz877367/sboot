package concurrency.volatitle;

import sun.misc.Contended;

//使用@Contended 配合使用JVM参数 ‐XX:‐RestrictContended
public class TestContended {
    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
        for (int i = 0; i < 100000000; i++) {
            pointer.x++;
        } });

        Thread t2 = new Thread(() -> {
        for (int i = 0; i < 100000000; i++) {
            pointer.y++;
        } });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(pointer.x+","+pointer.y);
        System.out.println(System.currentTimeMillis() - start);
    }
}

class Pointer {
    @Contended
    volatile long x;
    //避免伪共享： 缓存行填充
     //long p1, p2, p3, p4, p5, p6, p7;
     volatile long y;
 }