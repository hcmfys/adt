package org.springbus;

public class WaterCalc  {


    static  int trap(int[] height) {
        int n=height.length;
        int ans=0;
        for(int i=1;i<n;i++ ){
            int l_max=0;
            int r_max=0;
            for(int j=i;j<n;j++) {
                r_max= Math.max(height[j],r_max);
            }
            for(int j=i;j>=0;j--) {
                l_max= Math.max(height[j],l_max);
            }
            ans+=Math.min(l_max, r_max)- height[i];

        }
        return ans;
    }
    public static  void main(String [] args) {
        int height[]={0,1,0,2,1,0,1,3,2,1,2,1};
       int a= trap(height);
       System.out.println(a);
    }
}
