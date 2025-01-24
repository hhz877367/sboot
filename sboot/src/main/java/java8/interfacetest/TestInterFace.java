package java8.interfacetest;


@FunctionalInterface
public interface TestInterFace  {

    //只允许有一个默认的未实现的方法
    public void test();

    default String getName(){ return "人参";}
    static String getName2(){ return  "鹿茸";}

}
