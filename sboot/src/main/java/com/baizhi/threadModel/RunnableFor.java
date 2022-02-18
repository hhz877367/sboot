package com.baizhi.threadModel;

public class RunnableFor implements Runnable {

  @Override
  public void run() {
    Thread.currentThread().setName("myRunAble");
    for(int i=0;i<10000;i++){
      System.out.println(Thread.currentThread().getName()+"-----"+i);
    }
  }
}
