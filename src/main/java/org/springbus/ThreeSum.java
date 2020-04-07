package org.springbus;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * <p>注意：答案中不可以包含重复的三元组。
 *
 * <p>
 *
 * <p>示例：
 *
 * <p>给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * <p>满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 *
 * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeSum {

  private boolean isExist(List<List<Integer>> data, int i, int j, int k) {
    //  System.out.println("i="+i +" j="+j +" k="+k);
    for (List<Integer> d : data) {
      int i1 = -1;
      int i2 = -1;
      int i3 = -1;

      for (int index = 0; index < d.size(); index++) {
        if (d.get(index) == i && i1 == -1) {
          i1 = index;
        } else if (d.get(index) == j && i2 == -1) {
          i2 = index;
        } else if (d.get(index) == k && i3 == -1) {
          i3 = index;
        }
      }
      if (i1 > -1 && i2 > -1 && i3 > -1) {
        return true;
      }
    }
    return false;
  }

  public List<List<Integer>> threeSum(int[] nums) {

    List<List<Integer>> arr = new ArrayList<>();

    Map<Integer, List<Integer>> map = new HashMap<>();

    int size = nums.length;
    for (int i = 0; i < size; i++) {

      if (map.get(nums[i]) == null) {
        map.put(nums[i], new ArrayList<>());
      }
      map.get(nums[i]).add(i);
    }

    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        int k = 0 - (nums[i] + nums[j]);
        // System.out.println("k=" + k);
        List<Integer> dList = map.get(k);
        if (dList != null) {
          for (int q = 0; q < dList.size(); q++) {
            int c = dList.get(q);
            if (c != i && c != j) {
              List<Integer> list = new ArrayList<>();
              boolean ok = isExist(arr, nums[i], nums[j], nums[c]);
              // System.out.println(" ok=" +ok);
              if (!ok) {
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[c]);
                arr.add(list);
              }
            }
          }
        }
      }
    }
    return arr;
  }

  public static void main(String[] args) {
    int[] arr = new int[] {-1, 0, 1, 2, -1, -4};
    arr =
        new int[] {
          -10, 5, -11, -15, 7, -7, -10, -8, -3, 13, 9, -14, 4, 3, 5, -7, 13, 1, -4, -11, 5, 9, -11,
          -4, 14, 0, 3, -10, -3, -7, 10, -5, 13, 14, -5, 6, 14, 0, 5, -12, -10, -1, -11, 9, 9, 1,
          -13, 0, -13, -1, 4, 0, -7, 8, 3, 14, -15, -9, -10, -3, 0, -15, -1, -2, 6, 9, 11, 6, -14,
          1, 1, -9, -14, 6, 7, 10, 14, 2, -13, -13, 8, 6, -6, 8, -9, 12, 7, -9, -11, 4, -4, -4, 4,
          10, 1, -12, -3, -2, 1, -10, 6, -13, -3, -1, 0, 11, -5, 0, -2, -11, -6, -9, 11, 3, 14, -13,
          0, 7, -14, -4, -4, -11, -1, 8, 6, 8, 3
        };

    List<List<Integer>> t = new ThreeSum().threeSum(arr);
    System.out.println(t);
  }
}
