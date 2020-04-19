package org.springbus;

import java.util.List;
import java.util.Random;

public class SkipNode {
    public int val;
    public SkipNode next;
    public List<SkipNode> nodes;
    int level = 1;
    private int MAX_LEVEL = 26;


    public SkipNode() {
    }
    public SkipNode(int val) {
        this.val = val;
    }

    public int getRandomLevel() {
        return new Random(System.currentTimeMillis()).nextInt() % MAX_LEVEL;
    }
}
