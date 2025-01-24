package algorithm._05排序._02_递进;

import java.util.Random;

public class _03快速排序 {
    //基准数  快速排序的手法就是，把右边小的放到自己的左边，把左边大的放到自己的右边。


    public static void main(String[] args) {
        int[] intArr = getIntArr();
        long l = System.currentTimeMillis();
        qSort(intArr,0,intArr.length-1);
        long l1 = System.currentTimeMillis();
        System.out.println("消耗时间"+(l1-l)+"毫秒");
    }

    public  static int[] getIntArr(){
        int[] a=new int[2000000]; //200万，1秒不到
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(20000000); //数据分数度 最大2千万
        }
        return a;
    }

    public static void qSort(int data[], int left, int right) {

        int base = data[left]; // 就是我们的基准数，取序列的第一个,不能用data[0]
        int ll = left; // 表示的是从左边找的位置
        int rr = right; // 表示从右边开始找的位置
        while (ll < rr) {
            // 从后面往前找比基准数小的数
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) { // 表示是找到有比之大的
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                rr--;
            }
        }
        // 肯定是递归 分成了三部分,左右继续快排，注意要加条件不然递归就栈溢出了
        if (left < ll)
            qSort(data, left, ll - 1);
        if (ll < right)
            qSort(data, ll + 1, right);

    }


}
