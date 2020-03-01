package org.springbus;

import java.util.Stack;

public class Numbers {

    public static int toNum(String t) {
        int n = 0;
        for (char c : t.toCharArray()) {
            n = 10 * n + (c - '0');
            //System.out.println("n="+n);
        }
        return n;
    }


    public static int calc(String s) {
        Stack<Character> lst = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            lst.push(c);
        }
        return help(lst);
    }

    public static int help(Stack<Character> lst) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        while (lst.size() > 0) {
            char c = lst.pop();
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            if (c == '(') {
                num = help(lst);
            } else {
                if ((!Character.isDigit(c) && c != ' ') || lst.size() == 0) {
                    if (sign == '+') {
                        stack.push(num);
                    } else if (sign == '-') {
                        stack.push(-num);
                    } else if (sign == '*') {
                        int pre = stack.pop();
                        stack.push(pre * num);
                    } else if (sign == '/') {
                        int pre = stack.pop();
                        stack.push(pre / num);
                    }
                    num = 0;
                    sign = c;
                }
                if (c == ')') {
                    break;
                }
            }
        }

        while (stack.size() > 0) {
            num += stack.pop();
        }
        return num;
    }

    public static void main(String[] args) {
        int s = toNum("458098");
        //System.out.println(s);
        System.out.println(calc("4 * (6+5) +(( 2 * 2 ) + 9 ) * 2 "));
    }
}
