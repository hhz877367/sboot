package com.baizhi.test;

import lombok.Data;

@Data
public class CountModel {
  public static Integer i=0;

  public void sum(){
    for(int i=0;i<10000;i++){
      this.i++;
    }
  }

  public static void main(String[] args) {
    for(int i=0;i<1000;i++){
      Thread thread = new Thread(){
        @Override
        public void run() {
          CountModel.i++;
        }
      };
      thread.start();
    }
    System.out.println(CountModel.i);

  }


}
