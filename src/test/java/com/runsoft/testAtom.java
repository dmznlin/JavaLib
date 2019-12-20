package com.runsoft;

import java.util.concurrent.atomic.AtomicInteger;

public class testAtom {
    private static AtomicInteger nInt = new  AtomicInteger(0);

    public static void main(String[] args) {
        nInt.set(10);
        System.out.println(nInt.intValue());
    }
}
