
import com.sun.javafx.scene.control.skin.VirtualFlow;

import javax.print.attribute.standard.PrinterURI;
import java.awt.dnd.DropTarget;
import java.text.BreakIterator;
import java.util.*;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * https://pic.leetcode-cn.com/fe32ae9cee9ec8e2545d038d80a8da70d809eed01c153c6f0076801baab5010d-39-1.png
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        // 排序是为了提前终止搜索
        Arrays.sort(candidates);
        dfs(candidates, len, target, 0, new ArrayDeque<>(), res);
        return res;
    }

    /**
     * @param candidates 数组输入
     * @param len        输入数组的长度，冗余变量
     * @param residue    剩余数值
     * @param begin      本轮搜索的起点下标
     * @param path       从根结点到任意结点的路径
     * @param res        结果集变量
     */
    private void dfs(int[] candidates, int len, int residue, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 在数组有序的前提下，剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();

        }
    }




    public static void main(String[] args) {
       long t1= System.currentTimeMillis();
        for(int i=0;i<1;i++) {
            int a[] = {2, 3, 5};
            //a = new int[]{3, 2, 7, 6};
          //a = new int[]{5, 10, 8, 4, 3, 12, 9};
            a=new int[] {6,8,12,5,9,3,4,11 ,31};
            int target = 31;
            List<List<Integer>> ret = new CombinationSum().combinationSum(a, target);
            System.out.println(ret);
        }
        long t2= System.currentTimeMillis();
        System.out.println("t2-t1= "+ (t2-t1));
    }
}
