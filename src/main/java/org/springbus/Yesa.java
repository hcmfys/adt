package org.springbus;

public class Yesa {

    static int func(int curYear) {
        // 最简子问题，结束条件
        if (curYear == 1990) return 0;
        // 自我调用，缩小规模
        return func(curYear - 1) + 1;
    }

    public static void main(String[] args) {
        int y = func(2020);
        System.out.println("y=" + y);
    }

}
