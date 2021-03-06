package org.springbus;

/**
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * <p>
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * <p>
 * 有效括号字符串类型与对应的嵌套深度计算方法如下图所示：
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
 * <p>
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
 * A 或 B 中的元素在原字符串中可以不连续。
 * A.length + B.length = seq.length
 * 深度最小：max(depth(A), depth(B)) 的可能取值最小。 
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
 * <p>
 * answer[i] = 0，seq[i] 分给 A 。
 * answer[i] = 1，seq[i] 分给 B 。
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 * 示例 2：
 * <p>
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 * 解释：本示例答案不唯一。
 * 按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
 * 像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 < seq.size <= 10000
 *  
 * <p>
 * 有效括号字符串：
 * <p>
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
 * 下述几种情况同样属于有效括号字符串：
 * <p>
 * 1. 空字符串
 * 2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 * 3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * 嵌套深度：
 * <p>
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 * <p>
 * 1. s 为空时，depth("") = 0
 * 2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 * 3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 * <p>
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepthAfterSplit {


    public static void main(String[] args) {

        int[] a = new MaxDepthAfterSplit().maxDepthAfterSplit("(()(())())");
        for (int i : a) {
            System.out.print(i + " ");
        }

        a = new MaxDepthAfterSplit().maxDepthAfterSplit2("(()(())())");
        System.out.println("");
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    /**
     * 方法一：用栈进行括号匹配
     * 思路及算法
     * <p>
     * 要求划分出使得最大嵌套深度最小的分组，我们首先得知道如何计算嵌套深度。我们可以通过栈实现括号匹配来计算：
     * <p>
     * 维护一个栈 s，从左至右遍历括号字符串中的每一个字符：
     * <p>
     * 如果当前字符是 (，就把 ( 压入栈中，此时这个 ( 的嵌套深度为栈的高度；
     * <p>
     * 如果当前字符是 )，此时这个 ) 的嵌套深度为栈的高度，随后再从栈中弹出一个 (。
     * <p>
     * 下面给出了括号序列 (()(())()) 在每一个字符处的嵌套深度：
     * <p>
     * 括号序列   ( ( ) ( ( ) ) ( ) )
     * 下标编号   0 1 2 3 4 5 6 7 8 9
     * 嵌套深度   1 2 2 2 3 3 2 2 2 1
     * 知道如何计算嵌套深度，问题就很简单了：只要在遍历过程中，我们保证栈内一半的括号属于序列 A，一半的括号属于序列 B，那么就能保证拆分后最大的嵌套深度最小，是当前最大嵌套深度的一半。要实现这样的对半分配，我们只需要把奇数层的 ( 分配给 A，偶数层的 ( 分配给 B 即可。对于上面的例子，我们将嵌套深度为 1 和 3 的所有括号 (()) 分配给 A，嵌套深度为 2 的所有括号 ()()() 分配给 B。
     * <p>
     * 此外，由于在这个问题中，栈中只会存放 (，因此我们不需要维护一个真正的栈，只需要用一个变量模拟记录栈的大小。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/you-xiao-gua-hao-de-qian-tao-shen-du-by-leetcode-s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {

        int ans[] = new int[seq.length()];

        boolean l = true;
        boolean r = true;
        for (int i = 0; i < seq.length(); ++i) {
            if (seq.charAt(i) == '(') {
                if (l) ans[i] = 1;
                l = !l;
            } else {
                if (r) ans[i] = 1;
                r = !r;
            }
        }
        return ans;
    }

    int[] maxDepthAfterSplit2(String seq) {
        int d = 0;
        int ans[] = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                ++d;
                ans[i] = (d % 2);
            } else {
                ans[i] = (d % 2);
                --d;
            }
        }
        return ans;
    }
}
