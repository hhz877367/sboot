package com.baizhi.util;
public class MyThread extends Thread {
  private   Integer count=0;

  @Override
  public void run() {
    getCount();
    System.out.println("线程"+Thread.currentThread().getName()+"------"+count);
  }


  public  Integer getCount(){
    System.out.println("执行---");
    return  count++;
  }

}
