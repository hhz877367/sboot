package test;
/**
 * @author Herman.Xiong
 * @date 2014年11月17日 14:57:11
 * @version V1.0
 */
public class Consumer implements Runnable {
    private Center center;

    public Consumer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            center.consume();
        }
    }

}