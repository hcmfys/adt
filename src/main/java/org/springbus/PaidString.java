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

    public static void main(String[] args) {
        String s="ccacc";  //aacxycaa
      String t=  longestPalindrome(s);
      System.out.println("ret="+ t);
    }

}
