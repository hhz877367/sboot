package com.hhz.model.datastruture;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    public static void main(String[] args) {
        Queue<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        //返回第一个元素，并且在队列里删除
        System.out.println(list.poll());
        //返回第一个元素，在队列里不删除
        System.out.println(list.peek());
        System.out.println(list.element());
        System.out.println(list.element());



        
    }
}
