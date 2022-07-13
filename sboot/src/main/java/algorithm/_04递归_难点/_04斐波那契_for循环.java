package algorithm._04递归_难点;

public class _04斐波那契_for循环 {

    public static void main(String[] args) {
        for(int i=1;i<1000;i++){
            System.out.println(i+"----"+fibonacciEasy(i));
        }
    }

    //求 斐波那契 的数值 非递归，为 时间复杂度为 N
    public static double fibonacciEasy(int n) {
        if(n<=2){
            return 1;
        }
        double f1 = 1;
        double f2 = 1;
        double temp=0;
        for (int j = 3; j <= n; j++) {
            temp=f1+f2;
            f1 = f2;
            f2 = temp;
        }
        return temp;
    }
}
