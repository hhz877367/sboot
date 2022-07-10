package test;
/**
 * @author Herman.Xiong
 * @date 2014年11月17日 14:55:43
 * @version V1.0
 */
public class Producer implements Runnable {
    private Center center;

    public Producer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            //产生客户
            center.produce();
        }
    }
}