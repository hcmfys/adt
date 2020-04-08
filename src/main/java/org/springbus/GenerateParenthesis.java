package org.springbus;

import com.sun.tools.javac.jvm.Gen;

import java.util.ArrayList;
import java.util.List;

/**
 * 出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParenthesis {


    List<String> caList=new ArrayList<>();
    void p(String s,int open,int close,int n) {
        if( ( open+close) == 2*n ) {
            caList.add(s);
        }
        if(open<n) {
            p(s+"(",open+1,close,n);
        }
        if(close<open) {
            p(s+")",open,close+1,n);
        }
    }
    public List<String> generateParenthesis(int n) {
        p("",0, 0,n);
        return  caList;
    }

    public  static  void main(String[] args){
        List<String> t=new GenerateParenthesis().generateParenthesis(3);
        System.out.println(t);
    }

}
