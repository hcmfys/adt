package org.springbus;

import javafx.beans.binding.ListExpression;
import org.testng.collections.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
//给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png
//示例:
//
//输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LetterCombinations {

    Map<String,List<String>> letterMap= null;
    private  List<String>  newArrayList(String s) {
        List<String> retList=new ArrayList<>();
        for(char c :s.toCharArray()) {
            retList.add(c+"");
        }
        return retList;
    }

    private  void initMap(){
        if(letterMap==null) {
            letterMap = new ConcurrentHashMap<>(16);
            letterMap.put("2", newArrayList("abc"));
            letterMap.put("3", newArrayList("def"));
            letterMap.put("4", newArrayList("ghi"));
            letterMap.put("5", newArrayList("jkl"));
            letterMap.put("6", newArrayList("mno"));
            letterMap.put("7", newArrayList("pqrs"));
            letterMap.put("8", newArrayList("tuv"));
            letterMap.put("9", newArrayList("wxyz"));
        }

    }
    LinkedList<String> retList = new LinkedList<>();
    List<String> allList = new ArrayList<>();
    public void dfs(String digits,int i) {

        if(i>digits.length()-1) {
            //System.out.println(retList);
            String ret="";
            for(int q=0;q<retList.size();q++){
                ret+=retList.get(q);
            }
            allList.add(ret);
            return  ;
        }
        String c = digits.charAt(i) + "";
        List<String> lettersList = letterMap.get(c + "");
        for ( int index=0;index<lettersList.size();index++) {
            String l = lettersList.get(index);
            retList.addLast(l);
            dfs(digits,i+1);
            retList.removeLast();
        }

    }

    public List<String> letterCombinations(String digits) {
        initMap();
        dfs(digits, 0);
        return allList;
    }

    public static void main(String[] args) {
        List<String> r = new LetterCombinations().letterCombinations("999");
        System.out.println(r);
    }

}
