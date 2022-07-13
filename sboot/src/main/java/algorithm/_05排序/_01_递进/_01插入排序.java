package algorithm._05排序._01_递进;

import java.util.Random;

public class _01插入排序 {
    //插入排序,打扑克牌一样的排序。把一个无序的集合，插入到一个有序的集合中。
    //i从1开始   j=i-1 ,拿i后面的依次往前已经排好序的比
    // 时间复杂度 n^2  最好的是0N
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

    public  static int[] getIntArr(){
        int[] a=new int[10];
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            int i1 = random.nextInt(100);
            a[i]=random.nextInt(100);
        }
        return a;
    }


}
