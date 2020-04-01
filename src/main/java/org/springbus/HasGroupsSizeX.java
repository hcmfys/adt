package org.springbus;

import sun.security.krb5.internal.crypto.Des;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

///给定一副牌，每张牌上都写着一个整数。
//
//此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
//
//每组都有 X 张牌。
//组内所有的牌上都写着相同的整数。
//仅当你可选的 X >= 2 时返回 true。
//
// 
//
//示例 1：
//
//输入：[1,2,3,4,4,3,2,1]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
//示例 2：
//
//输入：[1,1,1,2,2,2,3,3]
//输出：false
//解释：没有满足要求的分组。
//示例 3：
//
//输入：[1]
//输出：false
//解释：没有满足要求的分组。
//示例 4：
//
//输入：[1,1]
//输出：true
//解释：可行的分组是 [1,1]
//示例 5：
//
//输入：[1,1,2,2,2,2]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[2,2]
//
//提示：
//
//1 <= deck.length <= 10000
//0 <= deck[i] < 10000
// 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class HasGroupsSizeX {


    private  boolean isExists( Map<Integer,Integer> map, int minSize ) {
        Iterator<Integer> v=map.values().iterator();
        while( v.hasNext()){
            if( v.next()  % minSize !=0) {
                 return  false;
            }
        }
        return  true;
    }

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1) {
            return false;
        }

        int minSize = 2;
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < deck.length; i++) {
            if (map.get(deck[i]) == null) {
                map.put(deck[i], 1);
            } else {
                map.put(deck[i], map.get(deck[i]) + 1);
            }
        }

        for (int i = minSize; i <=deck.length; i++) {
            boolean ret = isExists(map, i);
            if (ret) {
                return ret;
            }
            continue;

        }
        return false;

    }

    public static void main(String[] args) {
        int a[]=new int[]{1,1,2,2,2,2,6};
        a=new int[]{1,1,1,2,2,2,3,3};
        a= new int[]{1,1};
        //a=new int[] {1,1,2,2,2,2};
        boolean ok=new HasGroupsSizeX().hasGroupsSizeX(a);
        System.out.println(ok );
    }

}

