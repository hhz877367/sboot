package algorithm._07树;

public class Test {
    public static void main(String[] args) {
        double[] arr=new double[]{14963.84,1000,286,14915.34,0.21,15698.53,766.98,78.78,15051.02,14620.63,0.25,14911.81,0.29};
        double sum=0;
        for (double i : arr) {
            sum=sum+i;
        }
        System.out.println(sum);
    }
}
