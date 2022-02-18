package com.hhz.model.datastruture;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//数组与指针
public class Day02 {

  public static void main(String[] args) throws Exception {
    test();
    /*String str=null;
    String fileName ="E:\\数据结构资料\\aaa.txt";
    InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
    BufferedReader br = new BufferedReader(isr);
    while ((str = br.readLine())!=null){
      System.out.println(str);
    }
    ArrayList<Object> objects = new ArrayList<>();
    objects.add(231);*/
  }

  // 常量池
  public  static void test(){
 /*     String str1="aaa";
      String str2=new String("aaa");
      String str3="aaa";
    System.out.println(str1==str2);
    System.out.println(str1.equals(str2));
    System.out.println(str1==str3);*/
    StringBuffer sb1 = new StringBuffer("aa");
    StringBuffer sb2 = new StringBuffer("aa");
    boolean equals = sb1.equals(sb2);
    System.out.println(equals);


  }
}
