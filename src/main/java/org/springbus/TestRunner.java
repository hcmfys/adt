package org.springbus;

public abstract class TestRunner {

    private TestRunner runner;

    public TestRunner() {
    }

    public static void info(Object o) {
        System.out.println(o);
    }

    public static void infoLine(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {

    }

    public abstract void run();
}
