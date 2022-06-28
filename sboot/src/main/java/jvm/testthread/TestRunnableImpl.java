package jvm.testthread;

public class TestRunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("aaaa");
    }

    public static void main(String[] args) {
        TestRunnableImpl t = new TestRunnableImpl();
        t.run();
    }
}
