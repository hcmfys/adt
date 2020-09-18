package org.springbus.tree;

import java.util.ArrayList;
import java.util.List;

public class CareTest {



    public  void care(int l, int r, String str, List<String> careList,int n) {

        if(r+l == 2 *n) {
            careList.add(str);
        }else {
            if (l<n) {
                care(l+1, r , str + "(", careList, n);
            }
            if(r<l){
                care(l, r+1, str + ")", careList, n);
            }
        }
    }

    public  void care() {
        List<String > strList=new ArrayList<>();
        care(0, 0, "", strList,3);
        System.out.println(strList);
    }


    public static void main(String[] args) {
        new CareTest().care();
    }


}
