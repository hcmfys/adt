package org.springbus.sk;

public class SkipListEntry {
  Integer key;
  Integer value;
  SkipListEntry right;
  SkipListEntry left;
  SkipListEntry down;
  SkipListEntry up;

  public SkipListEntry(Integer key, Integer value) {
    this.key = key;
    this.value = value;
  }

  public String toString() {
    return "(" + key + "," + value + ")";
  }

  public int pos; // 与数据结构无关，只为输出方便
}
