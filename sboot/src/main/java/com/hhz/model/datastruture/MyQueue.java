package com.hhz.model.datastruture;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(10);
        myQueue.push(0);
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        myQueue.push(6);
        myQueue.push(7);
        myQueue.push(8);
        myQueue.push(9);
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop(); //8个 ，剩余 89
        myQueue.print();

    }


    private int data[];
    private int head = 0;
    private int tail = 0;

    private int n = 0; //数组的总共大小
    private int size=0;


    public MyQueue(int n) {
        data = new int[n];
        this.n = n;
    }

    //入队
    public boolean push(int value) {
        //如果队列已满
        if (size==n) {
            return false;
        }
        //寻找tail 的下标
        data[tail] = value;
        tail = (tail + 1) % n;
        size++;
        return true;
    }


    public int pop() {
        //查看队列是否为空
        if (size==0) {
            return -1;
        }
        int m=data[head];
        head = (head + 1) % n;
        size--;
        return m;
    }

    //遍历
    public void print(){
        for(;head!=tail;head=(head+1)%n){
            System.out.println(data[head]);
        }
    }

}
