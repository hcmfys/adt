package org.springbus.tree;

public class TrieNode {
    public char val;
    public TrieNode[] childs = new TrieNode[26];
    public boolean isWord = false;
    public TrieNode(char c) {
        this.val = c;
    }
}
