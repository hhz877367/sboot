package com.baizhi.entity.excelEntity;

public class GetIndexUtils {
    public static int getIndex(int[] array, int number) {
        int index = -1;
        for(int i=0;i<array.length;i++){
            if(i==0){
                if(number<=array[i]){
                    index=i;
                }
            }else{
                if(number>array[i-1]&&number<=array[i]){
                    index=i;
                }
            }
        }
        if(index==-1){
            index=array.length-1;
        }
        return index;
    }
}
