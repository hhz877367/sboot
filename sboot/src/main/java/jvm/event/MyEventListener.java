package jvm.event;

import java.util.EventListener;

public interface MyEventListener extends EventListener {
    public void handleEvent (MyEvent me);
}
