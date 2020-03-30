package org.springbus;

// 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1:
//
// 输入: 121
// 输出: true
// 示例 2:
//
// 输入: -121
// 输出: false
// 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 示例 3:
//
// 输入: 10
// 输出: false
// 解释: 从右向左读, 为 01 。因此它不是一个回文数。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/palindrome-number
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class IsPalindrome {

  String itoa(int x) {
    boolean isP = false;
    String ret = "";
    if (x < 0) {
      isP = true;
      x = -x;
    }
    int t = x;
    while (x > 0) {
      int y= x % 10;
      x = x / 10;
      ret += (char) (y + '0' );
    }

    ret=reverse(ret);
    System.out.println(ret);
    return ret;
    }

  String reverse(String ret) {
    int size = ret.length();
    String r = "";
    for (int i = size - 1; i >= 0; i--) {
      r += ret.charAt(i);
    }
    return r;
    }

  public boolean isPalindrome(int x) {

    if (x < 0) {
      return false;
    }
    String xx = itoa(x);
    return xx.equals(reverse(xx));
    }


    public static void main(String[] args) {
        int a=1221;
        boolean ok=new IsPalindrome().isPalindrome(a);
        System.out.println(ok );
    }

}
