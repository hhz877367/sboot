package com.hhz.model.idea;

public class Recursion {

    public static void main(String[] args) {

        //尾递归法求N的阶乘
       /* System.out.println(tailFactorial(5,1));*/
        //最基础版求斐波那契的值
        /* for(int i=1;i<100;i++){
            System.out.println(i+"----"+fibonacci(i));
        }*/
         //求N个斐波那契 的数值
 /*       nfibonacci(50);*/
        //求 斐波那契 的数值 非递归，为 时间复杂度为 N
     /*    for(int i=1;i<100;i++){
            System.out.println(i+"----"+fibonacciEasy(i));
        }*/
        //尾递归
    /*    int i = tailFactorial(5, 1);
        System.out.println(i);*/

        //尾递归
          for(int i=1;i<30;i++){
            System.out.println(i+"----"+fibonacciTail(i,1,1));
        }
    }

    //求一个数N的阶乘
    public static int factorial(int n) {
        if (n == 1) return n;
        return n * factorial(n - 1);
    }
    //尾递归
    public  static int tailFactorial(int n ,int result){
        System.out.println(n+"-----"+result);
        if(n<=1) return result;
        return  tailFactorial(n-1,n*result);
    }

    //求第N个 斐波那契 的数值 时间复杂度为 2的N次方
    public static int fibonacci(int n) {
        if (n <= 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
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

    // 尾递归法求斐波那契
    public static double fibonacciTail(int n,int pre,int res){
        if(n<=2) return res;
        return  fibonacciTail(n-1,res,pre+res);
    }

    // 求n个数，每个斐波那契的数值
    public static void nfibonacci(int length) {
        double f1 = 1;
        double f2 = 1;
        for (int j = 1; j < length; j++) {
            if (j <= 2) {
                System.out.println(j + "-----" + f1);
                continue;
            }
            System.out.println(j + "-----" + (f1 + f2));
            // 这一步非常重要，用temp 记录f1 的值，每次相加后，改变f1 f2 的值，为下次相加做准备
            double temp=f1;
            f1 = f2;
            f2 = temp + f2;
        }
    }


}
