package sheji_moshi_23.Adaptation;

public class objectAdapterTest {

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        adapter.outPut5v();

    }

}


class Adaptee{
    public int create220V(){
        System.out.println("生产220V电压");
        return 220;
    }
}

interface  Target{
    int outPut5v();
}

class Adapter implements Target{

    private Adaptee adaptee;

    public  Adapter(Adaptee adaptee){
        this.adaptee=adaptee;
    }

    @Override
    public int outPut5v() {
        int v = adaptee.create220V();
        System.out.println("进行逻辑处理");
        return 5;
    }
}