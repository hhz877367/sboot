package com.hhz.model.soft;

import java.util.Arrays;

public class InsertSort {

    //插入排序,打扑克牌一样的排序。把一个无序的集合，插入到一个有序的集合中。
    // 时间复杂度 n^2
    public static void main(String[] args) {
        int[] a={6,7,2,1,0,8,9};
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
        for(int k=0;k<a.length;k++){
            System.out.print(a[k]+",");
        }
        System.out.println();
    }




}
