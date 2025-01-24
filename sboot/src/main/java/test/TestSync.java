package test;

public class TestSync {
    public static void main(String[] args) {
        try {
            int a=0;
            int b=1;
            System.out.println(b/a);
        }catch (ExceptionInInitializerError e){
            e.printStackTrace();
            System.out.println("我就不报错了");
        }
    }
}
