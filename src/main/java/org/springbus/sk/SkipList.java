package org.springbus.sk;


import java.util.Random;

public class SkipList {

    private SkipNode head;
    private SkipNode tail;

    /**
     * 当前列表行的最大值
     */
    private int line;
    private Random random = new Random();
    private int MAX_LENGTH = 6;

    public SkipList() {
        this.line = MAX_LENGTH;
        this.head = new SkipNode(Integer.MIN_VALUE, MAX_LENGTH);

        this.head.setLineNum(MAX_LENGTH);
        SkipLine[] lines = new SkipLine[MAX_LENGTH];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new SkipLine();
            lines[i].setNext(null);
        }
        this.head.setPrev(null);
        this.head.setLine(lines);
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(13);
        skipList.insert(22);
        skipList.insert(84);
        skipList.insert(75);
        skipList.insert(89);
        skipList.insert(80);
    }

    private int getRandLine() {
        random.setSeed(System.currentTimeMillis());
        int l = random.nextInt(MAX_LENGTH) % MAX_LENGTH;
        if (l <= 0) {
            return 1;
        }
        return l;
    }

    public void insert(int data) {
        int curLineNum = getRandLine();

        SkipNode root = this.head;
        SkipNode[] preLine = new SkipNode[line];

        for (int i = line - 1; i >= 0; i--) {
            if (root == null) {
                break;
            }

            while (root != null) {
                SkipLine[] lines = root.getLine();
                SkipLine skipLine = lines[i];
                if (root.getData() > data) {
                    preLine[i] = root.getPrev();
                    root = preLine[i];
                    break;
                }
                if (root.getData() == data) {
                    return;
                }
                preLine[i] = root;
                if (skipLine.getNext() == null) {
                    break;
                } else {
                    root = skipLine.getNext();
                }
            }

        }

        SkipNode curNode = new SkipNode(data, curLineNum);
        for (int i = 0; i < curLineNum; i++) {
            SkipNode nextNode = preLine[i].getLine()[i].getNext();
            preLine[i].getLine()[i].setNext(curNode);
            curNode.setPrev(preLine[i]);
            curNode.getLine()[i].setNext(nextNode);
            preLine[i].setNext(curNode);
        }
        if (line < curLineNum) {
            line = curLineNum;
            this.head.setLineNum(line);
        }

    }
}
