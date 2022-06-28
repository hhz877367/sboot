package jvm.testscope;

import com.baizhi.entity.HhzTestWait;

public class TestScopt {
    public static void main(String[] args) {
        HhzTestWait hhzTestWait = new HhzTestWait();
        HhzTestWait hhzTestWait1= new HhzTestWait();
        HhzTestWait hhzTestWait2 = new HhzTestWait();
        HhzTestWait hhzTestWait3 = new HhzTestWait();
        System.out.println(hhzTestWait1.toString());
        System.out.println(hhzTestWait2.toString());
        System.out.println(hhzTestWait3.toString());
        hhzTestWait.test();
        hhzTestWait2.test();
        hhzTestWait3.test();
        hhzTestWait1.test();
    }
}
