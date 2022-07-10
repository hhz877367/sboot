package com.baizhi.test;

public class TestCPU {
    public static void main(String[] args) {
        for(int i=0;i<=12;i++){
            new Thread(()->{
                while (true){
                    int k=1;
                    k++;
                    int j=1;
                    j++;
                }
            }).start();
        }

    }
}
