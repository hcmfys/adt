package org.springbus;

public abstract class TestRunner {

  public  TestRunner(){
  }
  public abstract void run();

  private TestRunner runner;

  public static void info(Object o) {
    System.out.println(o);
  }

  public static void infoLine(Object o) {
    System.out.println(o);
  }

  public static void main(String[] args) {

  }
}
