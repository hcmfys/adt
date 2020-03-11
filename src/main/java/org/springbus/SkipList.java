package org.springbus;



import java.util.ArrayList;

public class SkipList {

    private SkipNode header;

    public void insert(int val) {
        if(header==null) {
            header=new SkipNode(val);
            int level=1;
            header.next=null;
            header.nodes=new ArrayList<>();
            header.nodes.add(header);
        }else {
            SkipNode node = header;
            int line = node.nodes.size();
            SkipNode targetNode = node;
            for (; ; ) {
                SkipNode nextNode = node.next;
                if (nextNode != null) {
                    if (nextNode.val > val) {
                        line--;
                        if (line < 0) {
                            targetNode = node;
                            break;
                        } else {
                            node = nextNode;
                        }
                    }
                }
            }
            SkipNode newNode = new SkipNode(val);
            newNode.level = newNode.getRandomLevel();
            newNode.nodes = new ArrayList<>();
            newNode.nodes.add(newNode);
            if (targetNode.val < val) {
                SkipNode next = targetNode.nodes.get(0).next;
                newNode.nodes.get(0).next = next;
                targetNode.nodes.get(0).next = next;
            }
        }

    }

    public void remove(int val) {

    }

    public SkipNode find(int val) {
        return null;
    }


}
