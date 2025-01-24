package jvm.参数调休;

public class 栈空间 {
    /*
    * ‐XX:MetaspaceSize=256M 设置元空间大小
    *
    *
    * -Xss128k  默认1M，如果设置 128k 则会大大提高方法的循环次数
    *
    * --默认1M 下循环次数为 23146
    * --128k  下的循环次数为 1080
    * */
    private static  int count=0;

    public static void  docount(){
        count++;
        docount();
    }

    public static void main(String[] args) {
        try {
            docount();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }

    }
}
