package org.springbus;


public class Divide {

    private int INT_MIN = Integer.MIN_VALUE;
    private int INT_MAX = Integer.MAX_VALUE;

    int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend > INT_MIN) return -dividend;// 只要不是最小的那个整数，都是直接返回相反数就好啦
            return INT_MAX;// 是最小的那个，那就返回最大的整数啦
        }
        long a = dividend;
        long b = divisor;
        int sign = 1;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long res = div(a, b);
        if (sign > 0) return res > INT_MAX ? INT_MAX : (int) res;
        return (int) -res;
    }

    int div(long a, long b) {  // 似乎精髓和难点就在于下面这几句
        if (a < b) return 0;
        long count = 1;
        long tb = b; // 在后面的代码中不更新b
        while ((tb + tb) <= a) {
            count = count + count; // 最小解翻倍
            tb = tb + tb; // 当前测试的值也翻倍
        }
        return (int) (count + div(a - tb, b));
    }


    public static void main(String[] args) {
        //-2147483648
        //-1
        int s = new Divide().divide(10, 3);
        System.out.println("s=" + s);
    }
}
