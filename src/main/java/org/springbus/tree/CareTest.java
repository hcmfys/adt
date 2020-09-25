package org.springbus.tree;

import java.util.ArrayList;
import java.util.List;

public class CareTest {



    public  void care(int l, int r, String str, List<String> careList,int n) {

        if(r+l == 2 *n) {
            careList.add(str);
            System.out.println("-------- end " +str  +" --------");
        }else {
            if (l<n) {
                System.out.println("before  0 l= " +l +"  r="+r   + " str=" +  str + "(");
                care(l+1, r , str + "(", careList, n);
                System.out.println("after 0 return -"+ str );
            }
            if(r<l){
                System.out.println("-before  1   ll= " +l +"  rr="+r);
                care(l, r+1, str + ")", careList, n);
                System.out.println("-after 1 return  -"+ str );
            }
        }
    }

    public  void care() {
        List<String > strList=new ArrayList<>();
        care(0, 0, "", strList,2);
        System.out.println(strList);
    }


    public static void main(String[] args) {
        new CareTest().care();
    }


}
