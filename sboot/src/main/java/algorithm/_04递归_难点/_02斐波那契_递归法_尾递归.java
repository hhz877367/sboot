package algorithm._04递归_难点;

public class _02斐波那契_递归法_尾递归 {
    public static void main(String[] args) {
        //最基础版求斐波那契的值   效率很低  时间复杂度为2 的N次方 递归的好处，代码简洁
         for(int i=1;i<100;i++){
            System.out.println(i+"----"+fibonacci(i));
        }

        //尾递归  效率很高, 思想就是----------- 把每次计算的结果报错起来往下传
        for(int i=1;i<1000;i++){
            System.out.println(i+"----"+fibonacciTail(i,1,1));
        }
    }

    //求第N个 斐波那契 的数值 时间复杂度为 2的N次方
    public static int fibonacci(int n) {
        if (n <= 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 尾递归法求斐波那契
    public static double fibonacciTail(int n,int pre,int res){
        if(n<=2) return res;
       return  fibonacciTail(n-1,pre,pre+res);
    }
}
