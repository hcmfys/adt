package org.springbus;

import sun.security.pkcs11.wrapper.CK_SSL3_KEY_MAT_PARAMS;

import java.util.Random;
import java.util.Scanner;

public class SkipListApp {

    private  Skip_List root;
    int maxLevel=32;
    private Random r;
    public  SkipListApp() {
        root = new Skip_List();
        r=new Random();
        root.level = 0;
        Skip_Node rootNode=new Skip_Node();
        root.head=rootNode;
        rootNode.levelNum=maxLevel;
        rootNode.level=new Skip_Level[ rootNode.levelNum];
        for(int i=0;i<rootNode.levelNum;i++) {
            Skip_Level skip_level= new Skip_Level() ;
            rootNode.level[i]=skip_level;
            skip_level.forward=null;
            rootNode.backward=null;
        }

    }

    public   int   getRandomLevel() {
        r.setSeed(System.currentTimeMillis());
        int l = r.nextInt() % maxLevel;
        if (l <= 0) {
            l = 1;
        }
        return l;
    }

    public  void insert(double score, Object data) {
        Skip_Node curNode=new Skip_Node();
        curNode.levelNum= getRandomLevel();
        curNode.score= score;
        curNode.data=data;
        Skip_Node rootNode=root.head;
        for( int i=rootNode.levelNum-1;i>=0;i-- ){
            Skip_Level curLevel= rootNode.level[i];
            if( curLevel!=null){

            }
        }


    }


    public static void main(String[] args) {

    }
}
