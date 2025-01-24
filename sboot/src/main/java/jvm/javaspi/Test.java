package jvm.javaspi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<TestJavaSpi> load = ServiceLoader.load(TestJavaSpi.class);
        Iterator<TestJavaSpi> iterator = load.iterator();
        for (TestJavaSpi testJavaSpi : load) {
            System.out.println(testJavaSpi.getClass().getName());
        }
    }
}
