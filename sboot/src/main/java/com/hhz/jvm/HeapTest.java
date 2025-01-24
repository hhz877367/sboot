package com.hhz.jvm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeapTest {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        int i=0;
        while (true) {
            i++;
            strings.add(new String("aaaaa"));
            try{
                Thread.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
