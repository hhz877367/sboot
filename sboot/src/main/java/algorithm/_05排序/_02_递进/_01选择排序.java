package algorithm._05排序._02_递进;

public class _01选择排序 {
    public static void main(String[] args) {
        //拿着第一个依次往后比较
        //不是重点，几乎不用。 n平方   并且不稳定， 相同位置的元素可能发生前后的变化  冒泡不会
        int[] intArr = new int[]{29,85,39,96,24,63,14,72,8,65};

        for (int i=0;i<intArr.length;i++){
            int loc=i;
            for (int j=i+1;j<intArr.length-1;j++){
                    if (intArr[j]<intArr[i]){
                        loc=j;
                    }
                    //交换
            }
        }
    }
}
