package jvm.逃逸分析;

import com.baizhi.entity.Student;
import com.baizhi.entity.TaoTiFenXi;

public class Test {
    public static void main(String[] args) {
        //   TaoTiFenXi student = createStudent();

        //

        //        System.out.println(student);

        long l = Runtime.getRuntime().maxMemory();
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println("最大内存"+l/1024/1025+"M");
        System.out.println("初始化内存"+l1/1024/1025+"M");

    }

    public static TaoTiFenXi createStudent(){
        TaoTiFenXi taoTiFenXi = new TaoTiFenXi();
        System.out.println(taoTiFenXi);
        return  taoTiFenXi;
    }
}
