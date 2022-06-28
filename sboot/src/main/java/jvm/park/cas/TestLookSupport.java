package jvm.park.cas;

import java.util.concurrent.locks.LockSupport;

public class TestLookSupport {
    public static void main(String[] args) {
        LockSupport.park();
    }
}
