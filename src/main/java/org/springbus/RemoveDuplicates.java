package org.springbus;

public class RemoveDuplicates {

    static int removeDuplicates(Integer a[]) {
        int slow = 0;
        int fast = 1;
        int n = a.length;
        while (fast < n) {
            if (a[fast] != a[slow]) {
                slow++;
                a[slow] = a[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {

        Integer a[] = {1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 333, 999};
        int length = removeDuplicates(a);
        for (int i = 0; i < length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        TreePrintUtil.pirnt(TreePrintUtil.makeTree(a));
        System.out.println("");
    }
}
