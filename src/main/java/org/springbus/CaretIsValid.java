package org.springbus;

import java.util.Stack;

// 20. 有效的括号
// 难度
// 简单
//
// 1470
//
// 收藏
//
// 分享
// 切换为英文
// 关注
// 反馈
// 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
// 输出: true
// 示例 2:
//
// 输入: "()[]{}"
// 输出: true
// 示例 3:
//
// 输入: "(]"
// 输出: false
// 示例 4:
//
// 输入: "([)]"
// 输出: false
// 示例 5:
//
// 输入: "{[]}"
// 输出: true
public class CaretIsValid {

    public static void main(String[] args) {
        String s = "([)]";
        s = "([)]";
        s = "()[]{}";
        s = "{[]}";
        System.out.println("caret  is= " + new CaretIsValid().isValid(s));
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.equals("")) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character p = stack.pop();
                if (p == '{') {
                    if (c != '}') {
                        return false;
                    }
                } else if (p == '[') {
                    if (c != ']') {
                        return false;
                    }
                } else if (p == '(') {
                    if (c != ')') {
                        return false;
                    }
                }

            }
        }
        return stack.empty();
    }
}
