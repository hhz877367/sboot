package com.hhz.jvm;

public class TestXss {
    private static int count=0;
    static void redo(){
        count++;
        redo();
    }

    public static void main(String[] args) {
        try{
            redo();
            System.out.println("执行了"+count+"次");
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("执行了"+count+"次");
        }

    }
}
