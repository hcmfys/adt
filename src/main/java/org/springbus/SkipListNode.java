package org.springbus;

public class SkipListNode<T> {
    public SkipListNode<T>[] next;
    public T key;

    public SkipListNode(T key, int i) {
        this.next = new SkipListNode[i];
        this.key = key;
    }


}
