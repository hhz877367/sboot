package sheji_moshi_23.单例模式;
// 懒汉式
public class Singleton02 {
    private volatile static Singleton02 singleton;

    public static Singleton02 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton02();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton02 singleton02 = new Singleton02();
        for (int i = 0; i <= 100; i++) {
            System.out.println(singleton02);
        }
    }
}
