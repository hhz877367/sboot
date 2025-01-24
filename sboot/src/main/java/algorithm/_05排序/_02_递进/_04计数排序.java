package algorithm._05排序._02_递进;

import com.baizhi.entity.Person;

import java.util.ArrayList;
import java.util.Random;

public class _04计数排序 {

    public static void main(String[] args) {
        Person person = new Person();
        ArrayList<Object> objects = new ArrayList<>(100);
        Object o = objects.get(10);
        System.out.println(o);

        // 只适用于数据能转换为int整型的数据排序，如果数值太大，超过数组的内存下标，则不适用。通常适用于数据散列百万以内，数据量亿级别的排序。
        // 计数排序的思想,利用数组下标，插入一遍就使其排好，效率 为 O(n),java最快排序，没有之一。
        // 数组下标当作分数，，值作为出现次数
        int[] intArr = getIntArr();;
        long l1 = System.currentTimeMillis();
        int[] result = countSoft(intArr);
        long l = System.currentTimeMillis();
       System.out.println("消耗时长"+(l-l1));
       for(int i=0;i<100000000;i++){
           if(i%1000000==0){
               System.out.println(result[i]);
           }
       }
    }
    public static int[]  countSoft(int[] arr){
        int length=arr.length;
        //创建一个大小一样的数组,进行存放最终结果
        int[] result = new int[length];
        //创建一个大小一样的数组,进行数值与下标反转
        int[] count = new int[length];
        //遍历一次
        for (int i : arr) {
            count[i]++;    //重点  下标等于分数  值等于这个分数出现的次数
        }
        //count的数据插入数据到result，在次遍历count
        for(int j=0,l=0;j<length;j++){
            //重点步骤，排除掉没有出现的分数，不纳入统计。
            if(count[j]>0){
                //把数据导入result
                for(int k=0;k<count[j];k++){
                    result[l]=j;
                    l++;
                }
            }
        }
        return result;
    }

    public  static int[] getIntArr(){
        int[] a=new int[100000000]; //1000万，1秒不到. 200万  30毫秒 1个亿，不到1秒
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(1000000); //数据分数度 最大2千万
        }
        return a;
    }
}
