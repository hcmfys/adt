package org.springbus;

import java.util.ArrayList;
import java.util.List;

public class PaidString {

    /**
     * main---如何寻找最长回文子串
     *
     * 对于这个问题，我们首先应该思考的是，给一个字符串 s，如何在 s 中找到一个回文子串？
     * 有一个很有趣的思路：既然回文串是一个正着反着读都一样的字符串，那么如果我们把 s 反转，
     * 称为 s'，然后在 s 和 s' 中寻找最长公共子串，这样应该就能找到最长回文子串。
     * 比如说字符串 abacd，反过来是 dcaba，它的最长公共子串是 aba，也就是最长回文子串。
     * 但是这个思路是错误的，比如说字符串 aacxycaa，反转之后是 aacyxcaa，
     * 最长公共子串是 aac，但是最长回文子串应该是 aa。
     * 虽然这个思路不正确，但是这种把问题转化为其他形式的思考方式是非常值得提倡的。
     * 下面，就来说一下正确的思路，如何使用双指针。
     * 寻找回文串的问题核心思想是：从中间开始向两边扩散来判断回文串。对于最长回文子串，就是这个意思：
     * @param s
     * @param l
     * @param r
     * @return
     */

    static   String palindrome(String s, int l, int r) {

        // 防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        int start = l + 1;
        int length = r - l - 1;
        return s.substring(start, start + length);
    }

    static  String  longestPalindrome(String s) {
        String res = "";
        ArrayList<String> resList=new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)

            if(resList.indexOf(s1)<0) {
                resList.add(s1);
            }
            if(resList.indexOf(s2)<0) {
                resList.add(s2);
            }
            res = res.length() >= s1.length() ? res : s1;
            res = res.length() >= s2.length() ? res : s2;

        }
        resList.stream().forEach(System.out::println);
        return res;
    }

    /**
     * Manacher 算法主要解决的是给出一个字符串，O(n)O(n)O(n) 复杂度下求出以字符串中任意一个节点为中心所能扩展的最大距离。
     * ————————————————
     * 记录 pip_ip
     * i
     * ​
     *   表示 iii 能向两边推（包括 iii）的最大距离，如果能求出 ppp 数组，那每一个回文串就都确定了。
     *
     * 我们假设 p[1～i]p[1～i]p[1～i] 已经求好了，现在要求 p[i]p[i]p[i]。假设之前能达到的最右边为 RRR，对应的中点为 pospospos，jjj 是 iii 的对称点。
     *
     * 第一种情况，i+p[j]<Ri+p[j]<Ri+p[j]<R，即 p[i]=p[j]p[i]=p[j]p[i]=p[j]。
     *
     * 第二种情况，i+p[j]≥Ri+p[j]\geq Ri+p[j]≥R，先设 p[i]=R−ip[i]=R-ip[i]=R−i ，然后再继续增加 p[i]p[i]p[i]，并令 pos=ipos=ipos=i，更新 RRR。
     * 由于 RRR 一定是递增的，因此时间复杂度为 O(n)O(n)O(n)，可以发现一个串本质不同的回文串最多有 nnn 个，因此只有 RRR 增加的时候才会产生本质不同的回文串。
     *
     * 算法特点
     *
     * ManacherManacherManacher 充分利用了回文的性质，从而达到线性时间。
     * ManacherManacherManacher 右端点递增过程中产生了所有的本质不同回文串，即一个串中本质不同回文串最多只有 nnn 个。
     * ManacherManacherManacher 算法的核心在于求出每一个节点往两边推的最远距离，所有涉及该算法的问题也都是在这个功能上做文章。
     * ————————————————

     * 原文链接：https://blog.csdn.net/qq_41552508/article/details/102262951
     * @param s1
     */

    static  void Manacher(char s1[]) {
        int len, tot, R = 0, pos = 0;
        //对字符串加'#'号
        len = s1.length + 1;
        char[] s2 = new char[2 * len + 1];
        int[] p = new int[2 * len + 1];
        int ln = 0;
        s2[0] = '$';
        s2[ln = 1] = '#';
        for (int i = 1; i <= len; i++) {
            s2[++ln] = s1[i];
            s2[++ln] = '#';
        }
        //求p[i]数组
        for (int i = 1; i <= ln; i++) {
            if (R >= i) {
                p[i] = Math.min(p[pos * 2 - i], R - i + 1);
            }
            if (p[i] + i > R) {
                for (; s2[R + 1] == s2[i * 2 - R - 1] && R + 1 <= ln && i * 2 - R - 1 <= ln; R++) ; //小心多组数据
                pos = i;
                p[i] = R - i + 1;
            }
        }
    }


    public static void main(String[] args) {
        String s="ccacc";  //aacxycaa
      String t=  longestPalindrome(s);
      System.out.println("ret="+ t);
    }

}
