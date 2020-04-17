package org.springbus;

import java.util.*;

/**
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 找到所有长度为 n 的中心对称数。
 *
 * 示例 :
 *
 * 输入:  n = 2
 * 输出: ["11","69","88","96"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strobogrammatic-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class FindStrobogrammatic {


    /**
     *
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n) {
        if(n<=0) {
            return new ArrayList<>();
        }

        map.put("6", "9");
        map.put("9", "6");
        map.put("8", "8");
        map.put("1", "1");
        map.put("0", "0");
        String[] arr = {"0","0","1", "1", "6", "8", "8", "9"};
        dfs(arr, 0,n, "",new LinkedList<>());
        return res;
    }

    private Map<String,String> map=new HashMap<>();
    List<String> res = new ArrayList<>();
    public  void  dfs(String[] arr,int start, int n ,String str, LinkedList<String> ret) {


        if (str.length() == n) {

            String ret2 = "";
            for (int i = n-1; i >=0; i--) {
                ret2 += map.get(str.charAt(i)+"");
            }

            System.out.println(str  +"  --" +ret2 );
            if (str.equals(ret2) && str.charAt(0)!='0') {
                if(!res.contains(str))
                res.add(str);
            }
        }
        if (str.length() == arr.length) {
            //System.out.println(ret);

        }
        for (int i = 0; i < arr.length; i++) {

            if(ret.contains(i+"")) continue;

            ret.add(i + "");
            String o=str;
            String t=o+""+arr[i];
            dfs(arr, i + 1,n,t, ret);
            ret.removeLast();
           // str=o;

        }


    }




    /**
     *
     * @param args
     */
    public  static  void main(String[] args) {
     List<String> arr=new FindStrobogrammatic().findStrobogrammatic(3);
     System.out.println( arr);
    }

}
