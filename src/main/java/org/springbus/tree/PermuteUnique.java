package org.springbus.tree;

import java.util.LinkedList;
import java.util.List;


//给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
// 示例:
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
// Related Topics 回溯算法

public class PermuteUnique {
    List<List<Integer>> retList = new LinkedList<>();

    public static void main(String[] args) {
        int a[] = {1, 2, 3};
        List<List<Integer>> ret = new PermuteUnique().permuteUnique(a);
        System.out.println(ret);
    }

    public void permuteUnique(LinkedList<Integer> list, int[] nums) {

        if (list.size() == nums.length) {
            retList.add(new LinkedList<>(list));

        } else {
            for (int i = 0; i < nums.length; i++) {
                int t = nums[i];
                System.out.println("i=" + i);
                if (list.contains(t)) {
                    continue;
                }
                list.add(t);
                System.out.println("i=" + i + "  start permuteUnique " + list);
                permuteUnique(list, nums);
                System.out.println("i=" + i + "  end  permuteUnique " + list);
                list.removeLast();
                System.out.println("i=" + i + "  删除后  permuteUnique " + list);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> aList = new LinkedList<>();
        permuteUnique(aList, nums);
        return retList;

    }
}
