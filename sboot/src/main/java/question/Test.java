package question;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        while (true){
            try {
                int sleepCount= new Random().nextInt(150)+50;
                System.out.println("睡眠"+sleepCount);
                Thread.sleep(sleepCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
