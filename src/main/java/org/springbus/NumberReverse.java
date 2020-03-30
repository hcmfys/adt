package org.springbus;

import javax.swing.text.rtf.RTFEditorKit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
// 输出: 321
//  示例 2:
//
// 输入: -123
// 输出: -321
// 示例 3:
//
// 输入: 120
// 输出: 21
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/reverse-integer
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class NumberReverse {

  public int reverse(int x) {
    boolean isP = false;
    if (x < 0) {
      isP = true;
      x=-x;
    }
    int t = x;
    long result = 0;
    LinkedList<Integer> numList = new LinkedList<>();
    while (x > 0) {
      int y = x % 10;
      x = x / 10;
      System.out.println(y);
      numList.push(y);
    }
    while (numList.size() > 0) {
      result = 10 * result + numList.removeLast();
    }
    if (isP) {
      result = -result;
    }
      System.out.println(result);
    if (result > Integer.MAX_VALUE-1) {
      result = 0;
    } else if (result < Integer.MIN_VALUE+1) {
      result = 0;
    }


    return  (int)result;
    }


    public int reverse2(int x) {
        int res = 0 ;
        while(x != 0){
            int temp = x % 10 + res * 10;
            if((temp - x % 10) / 10 != res){ //最关键的一步
                return 0 ;
            }
            res = temp ;
            x /= 10 ;
        }
        return res ;
    }



    public static void main(String[] args) {
        int  x=1534236469;
        int reverse =new  NumberReverse().reverse(x);
        System.out.println(reverse);
    }
}
