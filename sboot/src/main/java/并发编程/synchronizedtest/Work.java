package 并发编程.synchronizedtest;

public class Work {
    public synchronized void work(String value){
        for (int i=0;i<10;i++){
            System.out.println(value + i );
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void work2(String value){
        for (int i=0;i<10;i++){
            System.out.println(value + i );
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
