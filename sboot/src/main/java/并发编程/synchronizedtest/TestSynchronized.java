package 并发编程.synchronizedtest;

public class TestSynchronized {
    private  Object object = "";
    private  static int sum = 0;

    public  static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(() -> {
                TestSynchronized object = new TestSynchronized();
                synchronized (object.object) {
                    for (int j = 0; j < 1000; j++) {
                        sum++;
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
