package org.springbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Longletter {


    /**
     *
     */
    public static List<Integer> getLessLetter(String s, String t) {
        int left = 0;
        int right = 0;
        List<Integer> retList = new ArrayList<>();
        HashMap<Character, Integer> windows = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        int match = 0;

        for (char c : t.toCharArray()) {
            if (needs.get(c) == null) {
                needs.put(c, 1);
            } else {
                needs.put(c, 1 + needs.get(c));
            }
        }
        while (right < s.length()) {
            char c1 = s.charAt(right);

            if (needs.get(c1) != null) {
                int nds = 0;
                if (windows.get(c1) == null) {
                    nds = 1;
                } else {
                    nds = windows.get(c1);
                    nds++;
                }
                windows.put(c1, nds);
                if (windows.get(c1) == needs.get(c1)) {
                    match++;
                }
            } else {
                right++;
                match = 0;
                windows.clear();
                continue;
            }
            right++;

            if (match == needs.size()) {
                retList.add(right - t.length());
                match = 0;
                windows.clear();
            }

        }

        return retList;

    }

    /**
     * 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public static String getLongListLetter(String s, String t) {
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int intMax = Integer.MAX_VALUE;
        int start = 0;
        HashMap<Character, Integer> windows = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        int match = 0;

        for (char c : t.toCharArray()) {
            if (needs.get(c) == null) {
                needs.put(c, 1);
            } else {
                needs.put(c, 1 + needs.get(c));
            }
        }

        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (needs.get(c1) != null) {
                int nds = 0;
                if (windows.get(c1) == null) {
                    nds = 1;
                } else {
                    nds = windows.get(c1);
                    nds++;
                }
                windows.put(c1, nds);
                if (windows.get(c1) == needs.get(c1)) {
                    match++;
                }
            }
            right++;

            while (match == needs.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                if (needs.get(c2) != null) {
                    int counts = windows.get(c2);
                    counts--;
                    windows.put(c2, counts);
                    if (windows.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }
                left++;
            }

        }
        return minLen == intMax ? "" :
                s.substring(start, minLen + start);

    }

    public static void main(String[] args) {
        String ret = getLongListLetter("EBBANCF", "ABC");
        System.out.println(ret);

        List<Integer> lst = getLessLetter("cbaebabacd", "abcd");
        System.out.println(lst);
    }
}
