package java8.interfacetest;

import java.util.LinkedHashSet;
import java.util.Spliterator;

public class TestJava8 {

    public static void main(String[] args) {
        //传统调用
        //匿名内部类调用
        //lambda表达式调用
        //lambda表达式调用简化版
        LinkedHashSet<String> set= new LinkedHashSet();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
    }
}
