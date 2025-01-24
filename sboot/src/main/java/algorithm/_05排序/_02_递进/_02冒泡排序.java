package algorithm._05排序._02_递进;


import java.util.Random;

public class _02冒泡排序 {
    //相邻的依次比较
    // n平方   稳定， 相同位置的元素可能发生前后的变化  冒泡
    public static void main(String[] args) {
        int[] intArr = new int[]{29,85,39,96,24,63,14,72,8,65};
        for (int i : intArr) {
            System.out.println(i);
        }
        System.out.println("------------冒泡排序升级版-------");
        // i要从0开始比，因为起初的两位需要比，i<intArr.length  ,第二层 j从0开始  j<intArr.length-i-1，罪好ON
        for(int i=0;i<intArr.length-1;i++){
            boolean isSorted  = true;
            for(int j=0;j<intArr.length-i-1;j++){
                if(intArr[j+1]<intArr[j]){
                    int temp=intArr[j];
                    intArr[j]=intArr[j+1];
                    intArr[j+1]=temp;
                    isSorted=false;
                }
            }
            if(isSorted)
                break;
        }
        for (int i : intArr) {
            System.out.println(i);
        }


    }

    public  static int[] getIntArr(){
        int[] a=new int[11];
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            int i1 = random.nextInt(100);
            a[i]=random.nextInt(100);
        }
        return a;
    }

}
