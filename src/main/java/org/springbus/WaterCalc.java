package org.springbus;

public class WaterCalc {


    static int trap(int[] height) {
        if (height.length <= 0) return 0;
        int n = height.length;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int l_max = 0;
            int r_max = 0;
            for (int j = i; j < n; j++) {
                r_max = Math.max(height[j], r_max);
            }
            for (int j = i; j >= 0; j--) {
                l_max = Math.max(height[j], l_max);
            }
            ans += Math.min(l_max, r_max) - height[i];

        }
        return ans;
    }

    /**
     * 二、备忘录优化
     * 之前的暴力解法，不是在每个位置 i 都要计算
     * r_max 和 l_max 吗？我们直接把结果都缓存下来，
     * 别傻不拉几的每次都遍历，这时间复杂度不就降下来了嘛。
     * 我们开两个数组 r_max 和 l_max 充当备忘录，
     * l_max[i] 表示位置 i 左边最高的柱子高度
     * ，r_max[i] 表示位置 i 右边最高的柱子高度。预先把这两个数组计算好，避免重复计算：
     */

    static int trapMemo(int[] height) {
        if (height.length <= 0) return 0;
        int n = height.length;
        int ans = 0;
        // 数组充当备忘录
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for (int i = 1; i < n - 1; i++)
            ans += Math.min(l_max[i], r_max[i]) - height[i];
        return ans;
    }

    /**
     * 这种解法的思路是完全相同的，但在实现手法上非常巧妙，
     * 我们这次也不要用备忘录提前计算了，而是用双指针边走边算，节省下空间复杂度。
     *
     * @param height
     * @return
     */
    static int trapBest(int[] height) {
        if (height.length <= 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;
        int ans = 0;

        int l_max = height[0];
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int height[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int a = trapBest(height);
        System.out.println(a);
    }
}
