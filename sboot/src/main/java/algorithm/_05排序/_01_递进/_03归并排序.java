package algorithm._05排序._01_递进;

import java.util.Arrays;
import java.util.Random;

public class _03归并排序 {
    /*
     *  归并排序: 就是先把一组数据，通过递归的方式，拆分成一个数为止。在递归合并的过程中，进行排序
     * nlogn;   拆分数组 logn  ,遍历数组 n
     * */

    public static void main(String[] args) {

        int data[] = getIntArr();
        long l = System.currentTimeMillis();
        megerSort(data, 0, data.length - 1);
        long l1 = System.currentTimeMillis();
        System.out.println("消耗时间"+(l1-l));
        //JDK里面的排序源码

    }

    public  static int[] getIntArr(){
        int[] a=new int[100000]; //200万，1秒不到
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(100000); //数据分数度 最大2千万
        }
        return a;
    }


    public static void megerSort(int data[], int left, int right) { // 数组的两端
        if (left < right) { // 相等了就表示只有一个数了 不用再拆了
            int mid = (left + right) / 2;
            megerSort(data, left, mid);
            megerSort(data, mid + 1, right);
            // 分完了 接下来就要进行合并，也就是我们递归里面归的过程
            meger(data, left, mid, right);
        }
    }

    public static void meger(int data[], int left, int mid, int right) {
        int temp[] = new int[data.length];		//借助一个临时数组用来保存合并的数据

        int point1 = left;		//表示的是左边的第一个数的位置
        int point2 = mid + 1;	//表示的是右边的第一个数的位置

        int loc = left;		//表示的是我们当前已经到了哪个位置了
        while(point1 <= mid && point2 <= right){
            if(data[point1] < data[point2]){
                temp[loc] = data[point1];
                point1 ++ ;
                loc ++ ;
            }else{
                temp[loc] = data[point2];
                point2 ++;
                loc ++ ;
            }
        }
        //接下来要干嘛呢？合并排序完成 了吗？
        while(point1 <= mid){
            temp[loc ++] = data[point1 ++];
        }
        while(point2 <= right){
            temp[loc ++] = data[point2 ++];
        }
        for(int i = left ; i <= right ; i++){
            data[i] = temp[i];
        }
    }

}
