package sheji_moshi_23.单例模式;

// 懒汉式 饿汉式直接返回对象
public class Singleton {
    private static Singleton singleton;

    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
