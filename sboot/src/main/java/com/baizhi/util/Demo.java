package com.baizhi.util;

 class A {
}

class B extends A{
}

 class C extends B{
}

public class Demo {
    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        A c = new C();
        Class<? extends A> b_a = b.getClass();
        Class  b_class=B.class;
        System.out.println("==================");
        System.out.println(b.getClass() == B.class);
        System.out.println(c.getClass() == B.class);
        System.out.println(b_a == B.class);
        System.out.println(b_a == b_class);

        System.out.println("eeeeeeeeeeeeeeeeee");
        System.out.println(b.getClass().equals(B.class));
        System.out.println(c.getClass().equals(B.class));

        System.out.println("iiiiiiiiiiiiiiiiii");
        System.out.println(b instanceof A);
        System.out.println(b instanceof B);
        System.out.println(b instanceof C);

        System.out.println("xxxxxxxxxxxxxxxxx");
        System.out.println(B.class.isInstance(a));
        System.out.println(B.class.isInstance(b));
        System.out.println(B.class.isInstance(c));
    }
}
