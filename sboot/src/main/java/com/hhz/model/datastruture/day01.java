package com.hhz.model.datastruture;

import com.baizhi.util.Utils;
import java.util.Date;

public class day01 {

  public static void main(String[] args) {

  }


  // 定义判断一个数是否是2 的N次方
  public static String N2(int n){
    if((n&(n-1))==0){
      return "存在";
    }else {
      return "不存在";
    }
  }



}
