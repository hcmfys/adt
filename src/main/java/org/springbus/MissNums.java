package org.springbus;

/**
 * 如何寻找消失的元素
 */
public class MissNums {

    /**
     * 对于异或运算（^），我们知道它有一个特殊性质：
     * 一个数和它本身做异或运算结果为 0，
     * 一个数和 0 做异或运算还是它本身。
     *
     * @param nums
     * @return
     */
    static int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 先和新补的索引异或一下
        res ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    /**
     * 由于异或运算满足交换律和结合律，所以总是能把成对儿的数字消去，留下缺失的那个元素的。
     * 至此，时间复杂度 O(N)，空间复杂度 O(1)，已经达到了最优，我们是否就应该打道回府了呢？
     * 如果这样想，说明我们受算法的毒害太深，随着我们学习的知识越来越多，反而容易陷入思维定式，
     * 这个问题其实还有一个特别简单的解法：等差数列求和公式。
     * 题目的意思可以这样理解：现在有个等差数列 0, 1, 2,..., n，其中少了某一个数字，请你把它找出来
     * <p>
     * 那这个数字不就是 sum(0,1,..n) - sum(nums) 嘛？
     *
     * @param nums
     * @return
     */
    static int missingNumberSum(int[] nums) {
        int n = nums.length;
        int res = n;
        for (int i = 0; i < n; i++) {
            res += i - nums[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int sum[] = new int[]{3, 0, 1};
        int lostNum = missingNumberSum(sum);
        System.out.println(lostNum);
    }

}
