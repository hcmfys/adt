package org.springbus;

/**
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 */
public class CountAndSay {

    /**
     *
     * @param n
     * @return
     */
    private String countNum(String n) {
        StringBuffer ret = new StringBuffer();
        char lastChar = n.charAt(0);
        int counts = 1;
        int index = 1;
        while (index < n.length()) {

            Character c = n.charAt(index);
            if (c != lastChar) {
                ret.append(counts + "" + lastChar);
                lastChar = c;
                counts = 1;
            } else {
                counts++;
            }
            index++;
        }
        ret.append(counts+""+lastChar);
        return ret.toString();
    }

    public String countAndSay(int n) {
        if(n==1) {
            return "1";
        }else {
            return countNum(countAndSay(n - 1));
        }

    }


    public static void main(String[] args) {
        String s=new CountAndSay().countAndSay(6);
        System.out.println(s);
    }

}
