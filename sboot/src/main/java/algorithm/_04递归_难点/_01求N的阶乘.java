package algorithm._04递归_难点;

public class _01求N的阶乘 {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(factorial(5,1));
    }

    public static int factorial(int n) {
        if (n == 1) return n;
        return n * factorial(n - 1);
    }

    public static int factorial(int n,int result) {
        if (n == 1) return result;
        return  factorial(n - 1,n*result);
    }


}
