package 并发编程.volatitle;

import com.baizhi.entity.Student;

public class Test {
    public static void main(String[] args) {
        String a= new String("3213123212312");
        lianxi(a);
        System.out.println(a);

        Student student = new Student();
        student.setAge("111");
        lianxi2(student);
        System.out.println(student.getAge());
    }

    public static void  lianxi(String a){
        a="2222";
    }

    public static void  lianxi2(Student a){
        a.setAge("22222");
    }
}
