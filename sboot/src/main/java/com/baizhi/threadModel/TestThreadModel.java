package com.baizhi.threadModel;

import com.baizhi.test.CountModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class TestThreadModel {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //testThreadFor();
   // testRunnableFor();
    //testCallable();
    //testExecutorService();
    testPool();
  }

  //测试实现thread接口
  public static void testThreadFor(){
    ThreadFor t1 = new ThreadFor();
    t1.start();
    for(int i=0;i<10000;i++){
      System.out.println(Thread.currentThread().getName()+"-----"+i);
    }
  }

  //测试实现RunnableFor接口
  public static void testRunnableFor(){
    Thread t1 = new Thread(new RunnableFor());
    t1.start();
    for(int i=0;i<10000;i++){
      System.out.println(Thread.currentThread().getName()+"-----"+i);
    }
  }

  //测试实现RunnableFor接口
  public static void testCallable() throws ExecutionException, InterruptedException {
    Integer sum=0;
    Date date1 = new Date();
    List<FutureTask<Integer> > futureList = new ArrayList<>();
    for(int i=0;i<100;i++){
      CallableFor callableFor = new CallableFor();
      callableFor.setI(i);
      FutureTask<Integer> task1 = new FutureTask<>(callableFor);
      futureList.add(task1);
      new Thread(task1).start();
    }
    for( FutureTask<Integer> f:futureList){
      Integer integer = f.get();
      sum=sum+integer;
    }
    Date date2 = new Date();
    System.out.println("消耗时间"+(date2.getTime()-date1.getTime()));
    System.out.println("最终sum" +sum);

  }

  //测试实现RunnableFor接口
  public static void testExecutorService() throws ExecutionException, InterruptedException {
    ExecutorService executors = Executors.newFixedThreadPool(100);
    Integer sum=0;
    Date date1 = new Date();
    List<  Future<Integer> > futureList = new ArrayList<>();
    for(int i=0;i<100;i++){
      CallableFor callableFor = new CallableFor();
      callableFor.setI(i);
      Future<Integer> submit = executors.submit(callableFor);
      futureList.add(submit);
    }
    for( Future<Integer> f:futureList){
      Integer integer = f.get();
      sum=sum+integer;
    }
    executors.shutdown();
    Date date2 = new Date();
    System.out.println("消耗时间"+(date2.getTime()-date1.getTime()));
    System.out.println("最终sum" +sum);
  }
  public static  void testPool(){
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ExecutorService executorService1 = Executors.newFixedThreadPool(100);
    ExecutorService executorService2 = Executors.newCachedThreadPool();
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(100);
    ExecutorService executorService3 = Executors.newWorkStealingPool();
  //  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();


  }
}
