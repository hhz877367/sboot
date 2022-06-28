package jvm.park.cas;

import sun.misc.Contended;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCAS {
    // CAS 底层采用原理       int offset=unsafaFactory.getFileOffset(unsafa,Entity.clalss,"x");
    @Contended
    static AtomicInteger sum=new AtomicInteger(0);
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            Thread thread = new Thread(() -> {
                try {
                    for(int j=0;j<1000;j++){
                        sum.incrementAndGet();
                    }
                }catch (Exception e){

                }finally {
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
