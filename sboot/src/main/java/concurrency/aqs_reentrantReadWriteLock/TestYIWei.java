package concurrency.aqs_reentrantReadWriteLock;

public class TestYIWei {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
    public static void main(String[] args) {
        /*int i = exclusiveCount(666666);
        System.out.println(i);*/

    }
    static int exclusiveCount(int c) {

        return c & EXCLUSIVE_MASK;

    }
}
