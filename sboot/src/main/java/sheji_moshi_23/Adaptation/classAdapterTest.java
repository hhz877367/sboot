package sheji_moshi_23.Adaptation;

public class classAdapterTest {
    public static void main(String[] args) {

        Adapter1 adapter1 = new Adapter1();
        adapter1.outPut5v();
    }

}


class Adaptee1{
    public int create220V(){
        System.out.println("生产220V电压");
        return 220;
    }
}

interface  Target1{
    int outPut5v();
}

class Adapter1  extends Adaptee1 implements Target{
    @Override
    public int outPut5v() {
        System.out.println("进行逻辑处理");
        return 5;
    }
}
