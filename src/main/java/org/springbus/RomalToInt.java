package org.springbus;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RomalToInt {

    private Map<String, Integer> dataMap = new HashMap<>();

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * IV 4
     * IX 9
     * XL 40
     * XC  90
     * CD  400
     * CM  900
     */
    private void initMap() {
        dataMap.put("I", 1);
        dataMap.put("V", 5);
        dataMap.put("X", 10);
        dataMap.put("L", 50);
        dataMap.put("C", 100);
        dataMap.put("D", 500);
        dataMap.put("M", 1000);
        dataMap.put("IV", 4);
        dataMap.put("IX", 9);
        dataMap.put("XL", 40);
        dataMap.put("XC", 90);
        dataMap.put("CD", 400);
        dataMap.put("CM", 900);

    }

    public int romanToInt(String s) {
        int rets = 0;
        initMap();
        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            String cc = "";
            if (i < s.length() - 1) {
                cc = s.charAt(i + 1) + "";
            }
            if (dataMap.get(c + cc) != null) {
                i++;
                rets += dataMap.get(c + cc);

            } else if (dataMap.get(c) != null) {
                rets += dataMap.get(c);
            } else {
                System.out.println(" 格式错误 ");
            }
        }
        return rets;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }


    /**
     * 于是，“将整数转换为罗马数字”的过程，就是用上面这张表中右边的数字作为“加法因子”去分解一个整数，
     * 目的是“分解的整数个数”尽可能少，因此，对于这道问题，类似于用最少的纸币凑成一个整数，贪心算法的规则如下：
     *
     * 每一步都使用当前较大的罗马数字作为加法因子，最后得到罗马数字表示就是长度最少的。
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号
            while (num >= nums[index]) {
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        int a = new RomalToInt().romanToInt("MCMXCIV");
        System.out.println(a);

        a = new RomalToInt().romanToInt("LVIII");
        System.out.println(a);
        String r = new RomalToInt().intToRoman(9999);
        System.out.println(r);
        r = new RomalToInt().intToRoman(8);
        System.out.println(r);
        int q=5;
        int ten=10;
        int time=0;
        int rs=0;
        for (;;) {
            rs=q-ten;
            if(rs<0) {
                break;
            }
            q=rs;
            time++;
        }
        System.out.println( time +" -->r="+ q);

    }


}
