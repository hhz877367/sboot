package algorithm._05排序._01_递进;

import java.util.Random;

public class test插入 {
    public static void main(String[] args) {
        int[] intArr = getIntArr();
        int[] charu = charu(intArr);
        for (int i : charu) {
            System.out.println(i);
        }


    }

    public  static  int[] charu(int[] arr){
        for (int i=1;i<arr.length;i++) {
            int temp=arr[i];
            int j=i-1;
            for(;j>=0;j--){
                if(arr[j]>temp){
                    arr[j+1]=arr[j];
                }else {
                    break;
                }
            }
            arr[j+1]=temp;
        }
        return arr;
    }



    public  static int[] getIntArr(){
        int[] a=new int[100]; //200万，1秒不到
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(100); //数据分数度 最大2千万
        }
        return a;
    }
}
