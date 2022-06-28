package com.hhz.model.soft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InsertSort {

    //插入排序,打扑克牌一样的排序。把一个无序的集合，插入到一个有序的集合中。
    // 时间复杂度 n^2
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] a = getIntArr();
        for(int i=1;i<a.length;i++){
            int temp=a[i];
            int j=i-1;
            for(;j>=0;j--){
                if(a[j]>temp){
                    a[j+1]=a[j];
                }else {
                    break;
                }
            }
            a[j+1]=temp;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("排序完成时间"+(l1-l)+"毫秒");
        for(int k=0;k<a.length;k++){
            System.out.print(a[k]+",");
        }
        System.out.println();
    }
    
    private  static int[] getIntArr(){
        int[] a=new int[200000];
        Random random = new Random();
        for(int i=0;i<200000;i++){
            int i1 = random.nextInt(100);
            a[i]=random.nextInt(100);
        }
        return a;
    }




}
