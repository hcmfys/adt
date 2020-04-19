package org.springbus.tree;

public class TrieTree {

    private TrieNode rootNode;

    public TrieTree() {
        rootNode = new TrieNode(' ');
    }

    public static void main(String[] args) {
        TrieTree ti = new TrieTree();
        ti.create("java");
        ti.create("javap");
        ti.create("jac");
        TrieNode node = ti.getRootNode();
        System.out.println(node);
        boolean ok = ti.search("ja");
        System.out.println("ja" + ok);

        ok = ti.search("jav");
        System.out.println("jav=" + ok);


        ok = ti.search("sc");
        System.out.println("sc=" + ok);
    }

    public TrieNode create(String word) {
        return create(word, rootNode);
    }

    public TrieNode getRootNode() {
        return rootNode;
    }

    private TrieNode create(String word, TrieNode root) {
        if (word == null || word.equals("")) {
            return null;
        }
        char c = word.charAt(0);
        int index = charIndex(c);
        TrieNode nextNode = root.childs[index];

        if (nextNode == null) {
            nextNode = new TrieNode(c);
            root.childs[index] = nextNode;
        }

        if (word.length() == 1) {
            return nextNode;
        } else {
            return create(word.substring(1), nextNode);
        }
    }

    private boolean search(String word) {
        if (word == null) {
            return false;
        }
        return search(word, rootNode);
    }

    /**
     * search
     *
     * @param word
     * @param root
     * @return
     */
    public boolean search(String word, TrieNode root) {
        if (word.length() == 1) {
            char c = word.charAt(0);
            int index = charIndex(c);
            return root.childs[index] != null;
        }
        char c = word.charAt(0);
        int index = charIndex(c);
        if (root.childs[index] == null) {
            return false;
        } else {
            return search(word.substring(1), root.childs[index]);
        }
    }

    private int charIndex(char c) {
        return c - 'a';
    }
}
