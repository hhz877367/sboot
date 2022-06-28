package concurrency.synchronizedtest;

public class TestSynchronized {
    private static Object object = "";
    private  static int sum = 0;

    public  static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                synchronized (object) {
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
