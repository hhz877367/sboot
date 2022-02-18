package com.baizhi.threadModel;

import java.util.concurrent.Callable;
import lombok.Data;

@Data
public class CallableFor implements Callable<Integer> {
  private Integer i;
  @Override
  public Integer call() throws Exception {
    Integer sum=0;
    for(int j=0;j<10000;j++){
      System.out.println(Thread.currentThread().getName()+"-----"+j);
      sum++;
    }
    return sum;
  }
}
