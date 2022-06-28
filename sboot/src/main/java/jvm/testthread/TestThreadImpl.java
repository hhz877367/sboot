package jvm.testthread;

import lombok.Data;
public class TestThreadImpl extends  Thread {

    private  String name;

    private  String age;

    public void setAge(String age){
        this.age=age;
    }


    public TestThreadImpl(String name,String age){
        this.name=name;
        this.age=age;
    }
    public TestThreadImpl(){

    }



    @Override
    public void run() {
        System.out.println("name="+name+"-----age="+age);
        System.out.println("TestThreadImpl");
    }

    public static void main(String[] args) {
        TestThreadImpl t = new TestThreadImpl("zs","ls");
        t.setAge("aaaa");
        t.start();

        new Thread(()->{
            System.out.println("测试线程是否开启");
        }).start();
    }
}
