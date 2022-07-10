package jvm.event;

public class TestMyEventListener implements MyEventListener    {




    public void handleEvent(MyEvent me)
    {
        System.out.println(me.getSource());
        System.out.println(me.getsName());
    }

    public TestMyEventListener() {
        MyEventSource mes = new MyEventSource();
        mes.addMyEventListener(this);
        mes.setName("niu");
    }



    public static void main(String args[])
    {
         new TestMyEventListener();
    }
}
