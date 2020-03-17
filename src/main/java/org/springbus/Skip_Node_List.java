package org.springbus;

import java.util.Random;

public class Skip_Node_List {

    int SKIP_LIST_MAX_LEVEL = 32;


    //函数实现
    public Skip_List create_skiplist() {
        Skip_List skiplist = new Skip_List();
        skiplist.head = new Skip_Node();

        skiplist.length = 0;
        skiplist.level = 0;

        skiplist.head.level = new Skip_Level[SKIP_LIST_MAX_LEVEL];
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            Skip_Level skipLevel = new Skip_Level();
            skiplist.head.level[i] = skipLevel;
        }
        skiplist.head.backward = null;
        skiplist.head.score = 0;
        skiplist.head.data = null;
        skiplist.head.levelNum = SKIP_LIST_MAX_LEVEL;

        skiplist.tail = null;

        return skiplist;
    }
    private Random r= new Random(System.currentTimeMillis());
    public int rand_level() {
        int level = 1;
        level = r.nextInt() % SKIP_LIST_MAX_LEVEL;
        if (level <= 0) {
            level = 1;
        }
        return level;
    }

    public void insert_skipnode(Skip_List skiplist, double score, Object data) {
        Skip_Node node = new Skip_Node();
        int level = rand_level();
        node.level = new Skip_Level[level];
        for (int i = 0; i < level; i++) {
            node.level[i] = new Skip_Level();
        }

        node.score = score;
        node.data = data;
        node.levelNum = level;
        //保存要插入节点的前面的各层指针
        Skip_Node[] update = new Skip_Node[SKIP_LIST_MAX_LEVEL];
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            update[i] = new Skip_Node();
        }
        Skip_Node start = skiplist.head;
        int maxlevel = skiplist.level;
        //从高层向下查找
        for (int k = maxlevel - 1; k >= 0; k--) {
            Skip_Node end = start;
            Skip_Node ptmp = null;
            //找到比score大的节点
            while ((ptmp = end.level[k].forward) != null && ptmp.score < score) {
                end = ptmp;
            }
            update[k] = end;
        }
        //如果新节点的层数大于当前最大层，则它多出来的几层的前指针为head的各层指针
        if (level > maxlevel) {
            for (int k = level - 1; k >= maxlevel; k--) {
                update[k] = skiplist.head;
            }
            skiplist.level = level;
        }

        for (int i = 0; i < level; i++) {
            node.level[i].forward = update[i].level[i].forward;
            update[i].level[i].forward = node;
            if (i == 0) {
                node.backward = update[i];
            }
        }

        if (skiplist.tail == null || node.level[0].forward == null) {
            skiplist.tail = node;
        }

        ++skiplist.length;
    }

    public Skip_Node find_skipnode(Skip_List skiplist, double score) {
        Skip_Node head = skiplist.head;
        int maxlevel = skiplist.level;
        Skip_Node ptmp = null;
        for (int k = maxlevel - 1; k >= 0; k--) {
            while ((ptmp = head.level[k].forward) != null && ptmp.score < score) {
                head = ptmp;
            }
            if (ptmp != null && ptmp.score == score) {
                return ptmp;
            }
        }
        return null;
    }

    public void find_skipnode_inrange(Skip_List skiplist, double startscore, double endscore, Skip_Node startnode,
                                      Skip_Node endnode) {
        if (startscore > endscore)
            return;
        int maxlevel = skiplist.level;
        Skip_Node start = skiplist.head;
        Skip_Node end = start;
        Skip_Node ptmp = null;
        Skip_Node ptmp2 = null;
        boolean isFindLeft = false;
        boolean isFindRight = false;
        for (int k = maxlevel - 1; k >= 0; k--) {
            while ((ptmp = end.level[k].forward) != null && !isFindRight && ptmp.score < endscore) {
                end = ptmp;
            }
            if (!isFindRight && ptmp != null && ptmp.backward != null && ptmp.backward.score <= endscore) {
                endnode = ptmp;
                isFindRight = true;
            }
            while ((ptmp2 = start.level[k].forward) != null && !isFindLeft && ptmp2.score < startscore) {
                start = ptmp2;
            }
            if (!isFindLeft && ptmp2 != null) {
                Skip_Node x = ptmp2.backward;
                if (x != null && ((x.score < startscore) || x == skiplist.head)) {
                    startnode = ptmp2;
                    isFindLeft = true;
                }
            }

            if (isFindLeft && isFindRight)
                break;
        }

    }

    public void remove_skipnode(Skip_List skiplist, Skip_Node node) {
        Skip_Node head = skiplist.head;
        Skip_Node[] update = new Skip_Node[SKIP_LIST_MAX_LEVEL];
        for (int k = node.levelNum - 1; k >= 0; k--) {
            Skip_Node x = head;
            Skip_Node y = null;
            while ((y = x.level[k].forward) != null && y != node) {
                x = y;
            }
            update[k] = x;
        }
        for (int k = node.levelNum - 1; k >= 0; k--) {
            update[k].level[k].forward = node.level[k].forward;
        }
        if (node.level[0].forward != null) {
            node.level[0].forward.backward = node.backward;
        }

        --skiplist.length;
    }

    public Skip_Node find_inrange_first(Skip_List skiplist, double startscore, double endscore) {
        Skip_Node start = null;
        Skip_Node end = null;
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (start != null)
            return start;
        return null;
    }

    public Skip_Node find_inrange_last(Skip_List skiplist, double startscore, double endscore) {
        Skip_Node start = null;
        Skip_Node end = null;
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (end != null)
            return end.backward;
        return null;
    }

    public void remove_inrange(Skip_List skiplist, double startscore, double endscore) {
        int maxlevel = skiplist.level;
        Skip_Node head = skiplist.head;
        Skip_Node start = null;
        Skip_Node end = null;
        //找到开始和结束位置，[start,end)
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (start == null)
            return;
        Skip_Node[] front = new Skip_Node[SKIP_LIST_MAX_LEVEL];
        Skip_Node[] rear = new Skip_Node[SKIP_LIST_MAX_LEVEL];
        //保存范围前面各层的指针
        Skip_Node ptmp = start.backward;
        int ncount = 0;
        while (ncount < maxlevel) {
            for (; ncount < ptmp.levelNum; ) {
                front[ncount] = ptmp;
                ++ncount;
            }
            ptmp = ptmp.backward;
        }
        //保存范围后的各层指针
        if (end != null) {
            ptmp = end.backward;
            ncount = 0;
            while (ncount < maxlevel && ptmp != start) {
                for (; ncount < ptmp.levelNum; ) {
                    rear[ncount] = ptmp;
                    ++ncount;
                }
                ptmp = ptmp.backward;
            }
        }
        //将前后连接
        for (int i = 0; i < ncount; i++) {
            if (rear[i] != null)
                front[i].level[i].forward = rear[i].level[i].forward;
            else
                front[i].level[i].forward = null;
        }
        //删除节点，更改skiplist->length
        if (end != null)
            end.backward = start.backward;
        int len = 0;
        for (; start != end; ) {
            Skip_Node del = start;
            start = start.level[0].forward;

            ++len;
        }
        skiplist.length -= len;
        //更新skiplist->level
        for (int k = maxlevel - 1; k >= 0; k--) {
            if (head.level[k].forward != null) {
                skiplist.level = k + 1;
                break;
            }
        }
    }

    public void destroy_skiplist(Skip_List skiplist) {
//        skip_node *head = skiplist->head;
//        skip_node *del = NULL;
//        while (head) {
//            del = head;
//            head = head->level[0].forward;
//            free(del->level);
//            free(del);
//        }
//        free(skiplist);
    }

    //								            106 [29]
    //								            106 [29]
    //						        105 [27]    106 [29]
    // 100 [26]					    105 [27]	106 [29]
    // 100 [26]					    105 [27]	106 [29]
    // 100 [26]					    105 [27]    106 [29]
    // 100 [26]					    105 [27]    106 [29]
    // 100 [26]					    105 [27]    106 [29]
    // 100 [26]					    105 [27]	106 [29]
    // 100 [26]					    105 [27]    106 [29]
    // 100 [26]					    105 [27]    106 [29]
    // 100 [26]                                           105 [27]	106 [29]
    // 100 [26]                                           105 [27]	106 [29]
    // 100 [26]                                           105 [27]    106 [29]
    // 100 [26]                                           105 [27]   106 [29]
    // 100 [26]  104 [14]                                 105 [27]   106 [29]
    // 100 [26]  104 [14]                                 105 [27]   106 [29]
    // 100 [26]  104 [14]                                 105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  103 [11]                       104 [14]  105 [27]   106 [29]
    // 100 [26]  101 [5]            103 [11]    104 [14]  105 [27]   106 [29]
    // 100 [26]  101 [5]            103 [11]    104 [14]  105 [27]   106 [29]
    // 100 [26]  101 [5]            103 [11]    104 [14]  105 [27]   106 [29]
    // 100 [26]  101 [5]            103 [11]    104 [14]  105 [27]   106 [29]
    // 100 [26]  101 [5]  102 [1]   103 [11]    104 [14]  105 [27]  106 [29]  107 [1]  108 [1]  109 [1]

    public void print_skiplist_all(Skip_List skiplist) {
        if (skiplist == null)
            return;
        System.out.println("====================================\n");
        Skip_Node head = skiplist.head;
        int len= head.levelNum;
        for(int i=len-1;i>=0;i--) {
            Skip_Level root = head.level[i];

            Skip_Node nextNode = root.forward;
            Skip_Node firstNode=nextNode;
            System.out.print("H->");
            int j=0;
            while (nextNode != null) {
                Skip_Level nextLevel = nextNode.level[i];
                if(j==0) {
                    j++;
                    int w1 = getOffSet(skiplist, firstNode);
                    System.out.print(String.format("%s", getEmpty(w1)));
                }
                //System.out.println("w="+w);
                System.out.print(String.format("%3d[%d]->", nextNode.data, nextNode.levelNum));
                nextNode = nextLevel.forward;
                int  w2 = getOffSet(skiplist, nextNode);
                int  w1 = getOffSet(skiplist, firstNode);
                int w = w2 - w1;
                System.out.print(String.format("%s", getEmpty(w)));
                firstNode = nextNode;


            }
            System.out.println("");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++\n");
    }
    private String getEmpty( int n    ) {
        String t="-------";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<n;i++){
            sb.append(t);
        }
        return   sb.toString();
    }

    private int getOffSet(Skip_List skiplist , Skip_Node lastNode   ) {
        if(lastNode==null) {
            return  0;
        }
        if (lastNode == skiplist.head) {
            return 0;
        } else {
            Skip_Node head = skiplist.head;
            Skip_Level root = head.level[0];
            Skip_Node nextNode = root.forward;
            int l = 0;
            for (; ; ) {
                l++;
                if (nextNode == null || nextNode.data==null) {
                    break;
                }

                if (nextNode.data!=null && nextNode.data.equals(lastNode.data)) {
                    break;
                }
                nextNode = nextNode.level[0].forward;
            }
            return l;
        }
    }

    public void print_skiplist(Skip_List skiplist) {
        if (skiplist == null)
            return;
        System.out.println("====================================\n");
        Skip_Node head = skiplist.head;
        while (head.level[0].forward != null) {
            head = head.level[0].forward;
            System.out.println(String.format("score:%f,level:%d,data:%d", head.score, head.levelNum,
                    head.data));
        }
        System.out.println("++++++++++++++++++++++++++++++++++++\n");
    }


    public  static  void test(){
        Skip_Node_List skip_nodeList = new Skip_Node_List();
        Skip_List skip = skip_nodeList.create_skiplist();

        for (int i = 0; i < 10; i++) {
            skip_nodeList.insert_skipnode(skip, i, i + 100);
        }

        skip_nodeList.print_skiplist(skip);

        Skip_Node start = new Skip_Node();
        Skip_Node end = new Skip_Node();
        skip_nodeList.find_skipnode_inrange(skip, 3, 6.3, start, end);
        for (; start.score < end.score; start = start.level[0].forward) {
            System.out.println(String.format("find result score:%f,level:%d,data:%d ",
                    start.score, start.levelNum, start.data));
        }
        skip_nodeList.remove_inrange(skip, 3, 6.3);
        skip_nodeList.print_skiplist(skip);

        start = skip_nodeList.find_skipnode(skip, 9);
        System.out.println(String.format("find result score:%f,level:%d,data:%d", start.score, start.levelNum, start.data));

        skip_nodeList.insert_skipnode(skip, -3, 789);
        skip_nodeList.print_skiplist(skip);
        skip_nodeList.insert_skipnode(skip, 54, 999);
        skip_nodeList.print_skiplist(skip);

        skip_nodeList.remove_inrange(skip, 100, 300);
        skip_nodeList.print_skiplist(skip);

        skip_nodeList.remove_inrange(skip, 50, 100);
        skip_nodeList.print_skiplist(skip);
        skip_nodeList.destroy_skiplist(skip);
    }
    public static void main(String[] args) {
        Skip_Node_List skip_nodeList = new Skip_Node_List();
        Skip_List skip = skip_nodeList.create_skiplist();

        for (int i = 0; i < 10; i++) {
            skip_nodeList.insert_skipnode(skip, i, i + 100);
        }

        skip_nodeList.print_skiplist_all(skip);




    }


}
