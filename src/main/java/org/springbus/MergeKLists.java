package org.springbus;

public class MergeKLists {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ll1=l1;
        ListNode ll2=l2;
        ListNode newNodew=null;
        ListNode lastNode=null;
        while(ll1!=null ||  ll2!=null) {
            int c=0;
            int a=ll1.val;
            int b=ll2.val;
            if(a>b) {
                ll2=ll2.next;
                c=b;
            }else{
                ll1=ll1.next;
                c=a;
            }

            System.out.println(c);
            if(lastNode==null){
                lastNode=new ListNode(c);
                newNodew=lastNode;
            }else{
                lastNode.next=new ListNode(c);
            }
        }

        return newNodew;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode node = lists[0];

        for (int i = 1; i < lists.length; i++) {
            node = mergeTwoLists(node, lists[i]);
        }
        return node;
    }
}
