package org.springbus;


/***
 * 动态规划之四键键盘
 */
public class Dp_Copy {

    /***
     * 这种思路会很容易理解，但是效率并不高，
     * 我们直接走流程：对于动态规划问题，
     * 首先要明白有哪些「状态」，有哪些「选择」。
     * 具体到这个问题，对于每次敲击按键，有哪些「选择」是很明显的：4 种，
     * 就是题目中提到的四个按键，分别是 A、C-A、C-C、C-V（Ctrl 简写为 C）。
     * 接下来，思考一下对于这个问题有哪些「状态」？或者换句话说，我们需要知道什么信息，
     * 才能将原问题分解为规模更小的子问题？
     * 你看我这样定义三个状态行不行：
     * 第一个状态是剩余的按键次数，用 n 表示；
     * 第二个状态是当前屏幕上字符 A 的数量，用 a_num 表示；
     * 第三个状态是剪切板中字符 A 的数量，用 copy 表示。
     * 如此定义「状态」，
     * 就可以知道 base case：
     * 当剩余次数 n 为 0 时，a_num 就是我们想要的答案。
     * 结合刚才说的 4 种「选择」，
     * 我们可以把这几种选择通过状态转移表示出来
     * @param N
     * @return
     */

    static int maxA(int N) {
        // # 可以按 N 次按键，屏幕和剪切板里都还没有 A
        return dp(N, 0, 0);
    }

    // #对于(n, a_num, copy) 这个状态，
    //  #屏幕上能最终最多能有 dp (n, a_num, copy)个 A
    static int dp(int n, int a_num, int copy) {
        //System.out.println("n="+n +" a-num="+a_num +" b-num="+copy);
        // #base  case
        if (n <= 0) return a_num;
        //#几种选择全试一遍，选择最大的结果
        int a = dp(n - 1, a_num + 1, copy);//# A
        int b = dp(n - 1, a_num + copy, copy);// # C - V
        int c = dp(n - 2, a_num, a_num); //# C - A C - C
        a = Math.max(a, b);
        a = Math.max(a, c);
        return a;
    }


    static    int maxAFinal(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            // 按 A 键
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
                // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        // N 次按键之后最多有几个 A？
        return dp[N];
    }


    public static void main(String[] args) {
        int t = maxAFinal(10);
        System.out.println("t=" + t);

        t = maxA(10);
        System.out.println("t=" + t);
    }
}
