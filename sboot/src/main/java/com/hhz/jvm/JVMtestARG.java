package com.hhz.jvm;

import java.lang.ref.SoftReference;

public class JVMtestARG {
/*    大量的对象被分配在eden区，eden区满了后会触发minor gc，
    可能会有99%以上的对象成为垃圾被回收掉，剩余存活 的对象会被挪到为空的那块survivor区，
    下一次eden区满了后又会触发minor gc，把eden区和survivor区垃圾对象回 收，
    把剩余存活的对象一次性挪动到另外一块为空的survivor区，因为新生代的对象都是朝生夕死的，
    存活时间很短，所 以JVM默认的8:1:1的比例是很合适的，让eden区尽量的大，survivor区够用即可，
    JVM默认有这个参数-XX:+UseAdaptiveSizePolicy(默认开启)，会导致这个8:1:1比例自动变化，
    如果不想这个比例有变 化可以设置参数-XX:-UseAdaptiveSizePolicy*/
     //-Xlog:gc* jdk11使用-Xlog:gc*  jdk8使用-XX:+PrintGCDetails
    //-XX:+PrintGCDetails
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation2 = new byte[8000*1024];
        allocation1 = new byte[60000*1024];
        allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
   
    }
}
