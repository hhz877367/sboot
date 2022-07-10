package concurrency;

public class Test {
    static final int EXCLUSIVE_MASK = (1 << 16) - 1;
    public static void main(String[] args) {
        System.out.println(EXCLUSIVE_MASK);
        int b=65536+65536;
        System.out.println(b & 65535);
    }
}
