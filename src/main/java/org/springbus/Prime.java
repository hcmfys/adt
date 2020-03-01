package org.springbus;

import java.util.Arrays;
import java.util.Vector;

public class Prime {

    static int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)

                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    static int countPrimes3(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i < n; i++)
            if (isPrim[i])
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    static int countPrimes2(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) count++;
        }

        return count;
    }

    static boolean isPrime(int n) {
        boolean success = true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return success;
    }


    static int countPrimesFinal(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }


    static int base = 1337;

    // 计算 a 的 k 次方然后与 base 求模的结果
    static int mypow(int a, int k) {
        // 对因子求模
        a %= base;
        int res = 1;
        for (int _ = 0; _ < k; _++) {
            // 这里有乘法，是潜在的溢出点
            res *= a;
            // 对乘法结果求模
            res %= base;
        }
        return res;
    }


    static int superPow(int a, Vector<Integer> b) {
        if (b.size() == 0) return 1;

        int last = b.lastElement();
        b.remove(b.size() - 1);

        int part1 = mypow(a, last);
        int part2 = mypow(superPow(a, b), 10);
        // 每次乘法都要求模
        return (part1 * part2) % base;
    }


    static int mypowFinal(int a, int k) {
        if (k == 0) return 1;
        a %= base;

        if (k % 2 == 1) {
            // k 是奇数
            return (a * mypowFinal(a, k - 1)) % base;
        } else {
            // k 是偶数
            int sub = mypowFinal(a, k / 2);
            return (sub * sub) % base;
        }
    }

    public static void main(String[] args) {

        long tt1 = System.currentTimeMillis();
        int t1 = countPrimes(10000000);
        long tt2 = System.currentTimeMillis();
        System.out.println("t1=" + t1 + "  cost time=" + (tt2 - tt1));
        tt2 = System.currentTimeMillis();
        int t2 = countPrimesFinal(10000000);
        long tt3 = System.currentTimeMillis();
        System.out.println("t2=" + t2 + "  cost time=" + (tt3 - tt2));

       int c= mypowFinal(100000000, 10000000);
       System.out.println(c);

    }
}
