package org.springbus;

public class MaxLearSunList {



    public  static    int getMaxSubList(int arr[]) {
        int preMax = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {

            preMax = Math.max(arr[i], preMax + arr[i]);
            max = Math.max(preMax, max);
        }

        return max;
    }
    public static void main(String[] args) {
       int arr[]={-2,1,-3,4,-1,2,1,-5,-5,4};
       int  a=getMaxSubList(arr);
       System.out.println("a="+a);
    }

}
