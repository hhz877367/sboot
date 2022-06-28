package java8.lamda03;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;

public class Test3 {
    public static void main(String[] args) {

        new Thread(()->{

            System.out.println("aaaa");

        }).start();
    }
}
