package org.springbus;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;
import sun.util.resources.cldr.xh.CurrencyNames_xh;

import java.util.Random;

public class Skip_Node_List {

    int SKIP_LIST_MAX_LEVEL = 32;

    static class skip_node {
        skip_level level[];            //保存多个层
        skip_node backward;        //后退指针后退步数为1
        int levelnum;        //层数
        double score;            //分值，作为key对跳跃表排序
        Object data;            //数据对象
    }

    static class skip_level {
        skip_node forward;        //前进指针
    }

    static class skip_list {
        skip_node head;            //表头拥有最大层数，不保存数据
        skip_node tail;            //指向尾节点
        int level;            //除表头之外的最大层数
        int length;            //跳跃表的长度，也就表示节点个数
    }


    //函数实现
    public skip_list create_skiplist() {
        skip_list skiplist = new skip_list();
        skiplist.head = new skip_node();

        skiplist.length = 0;
        skiplist.level = 0;

        skiplist.head.level = new skip_level[SKIP_LIST_MAX_LEVEL];
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            skip_level skipLevel = new skip_level();
            skiplist.head.level[i] = skipLevel;
        }
        skiplist.head.backward = null;
        skiplist.head.score = 0;
        skiplist.head.data = null;
        skiplist.head.levelnum = SKIP_LIST_MAX_LEVEL;

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

    public void insert_skipnode(skip_list skiplist, double score, Object data) {
        skip_node node = new skip_node();
        int level = rand_level();
        node.level = new skip_level[level];
        for (int i = 0; i < level; i++) {
            node.level[i] = new skip_level();
        }

        node.score = score;
        node.data = data;
        node.levelnum = level;
        //保存要插入节点的前面的各层指针
        skip_node[] update = new skip_node[SKIP_LIST_MAX_LEVEL];
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            update[i] = new skip_node();
        }
        skip_node start = skiplist.head;
        int maxlevel = skiplist.level;
        //从高层向下查找
        for (int k = maxlevel - 1; k >= 0; k--) {
            skip_node end = start;
            skip_node ptmp = null;
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

    public skip_node find_skipnode(skip_list skiplist, double score) {
        skip_node head = skiplist.head;
        int maxlevel = skiplist.level;
        skip_node ptmp = null;
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

    public void find_skipnode_inrange(skip_list skiplist, double startscore, double endscore, skip_node startnode,
                                      skip_node endnode) {
        if (startscore > endscore)
            return;
        int maxlevel = skiplist.level;
        skip_node start = skiplist.head;
        skip_node end = start;
        skip_node ptmp = null;
        skip_node ptmp2 = null;
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
                skip_node x = ptmp2.backward;
                if (x != null && ((x.score < startscore) || x == skiplist.head)) {
                    startnode = ptmp2;
                    isFindLeft = true;
                }
            }

            if (isFindLeft && isFindRight)
                break;
        }

    }

    public void remove_skipnode(skip_list skiplist, skip_node node) {
        skip_node head = skiplist.head;
        skip_node[] update = new skip_node[SKIP_LIST_MAX_LEVEL];
        for (int k = node.levelnum - 1; k >= 0; k--) {
            skip_node x = head;
            skip_node y = null;
            while ((y = x.level[k].forward) != null && y != node) {
                x = y;
            }
            update[k] = x;
        }
        for (int k = node.levelnum - 1; k >= 0; k--) {
            update[k].level[k].forward = node.level[k].forward;
        }
        if (node.level[0].forward != null) {
            node.level[0].forward.backward = node.backward;
        }

        --skiplist.length;
    }

    public skip_node find_inrange_first(skip_list skiplist, double startscore, double endscore) {
        skip_node start = null;
        skip_node end = null;
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (start != null)
            return start;
        return null;
    }

    public skip_node find_inrange_last(skip_list skiplist, double startscore, double endscore) {
        skip_node start = null;
        skip_node end = null;
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (end != null)
            return end.backward;
        return null;
    }

    public void remove_inrange(skip_list skiplist, double startscore, double endscore) {
        int maxlevel = skiplist.level;
        skip_node head = skiplist.head;
        skip_node start = null;
        skip_node end = null;
        //找到开始和结束位置，[start,end)
        find_skipnode_inrange(skiplist, startscore, endscore, start, end);
        if (start == null)
            return;
        skip_node[] front = new skip_node[SKIP_LIST_MAX_LEVEL];
        skip_node[] rear = new skip_node[SKIP_LIST_MAX_LEVEL];
        //保存范围前面各层的指针
        skip_node ptmp = start.backward;
        int ncount = 0;
        while (ncount < maxlevel) {
            for (; ncount < ptmp.levelnum; ) {
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
                for (; ncount < ptmp.levelnum; ) {
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
            skip_node del = start;
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

    public void destroy_skiplist(skip_list skiplist) {
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

    public void print_skiplist_all(skip_list skiplist) {
        if (skiplist == null)
            return;
        System.out.println("====================================\n");
        skip_node head = skiplist.head;
        int len= head.levelnum;
        for(int i=len-1;i>=0;i--) {
            skip_level root = head.level[i];

            skip_node nextNode = root.forward;
            skip_node firstNode=nextNode;
            System.out.print("H->");
            int j=0;
            while (nextNode != null) {
                skip_level nextLevel = nextNode.level[i];
                if(j==0) {
                    j++;
                    int w1 = getOffSet(skiplist, firstNode);
                    System.out.print(String.format("%s", getEmpty(w1)));
                }
                //System.out.println("w="+w);
                System.out.print(String.format("%3d[%d]->", nextNode.data, nextNode.levelnum));
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

    private int getOffSet(skip_list skiplist ,skip_node lastNode   ) {
        if(lastNode==null) {
            return  0;
        }
        if (lastNode == skiplist.head) {
            return 0;
        } else {
            skip_node head = skiplist.head;
            skip_level root = head.level[0];
            skip_node nextNode = root.forward;
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

    public void print_skiplist(skip_list skiplist) {
        if (skiplist == null)
            return;
        System.out.println("====================================\n");
        skip_node head = skiplist.head;
        while (head.level[0].forward != null) {
            head = head.level[0].forward;
            System.out.println(String.format("score:%f,level:%d,data:%d", head.score, head.levelnum,
                    head.data));
        }
        System.out.println("++++++++++++++++++++++++++++++++++++\n");
    }


    public  static  void test(){
        Skip_Node_List skip_nodeList = new Skip_Node_List();
        skip_list skip = skip_nodeList.create_skiplist();

        for (int i = 0; i < 10; i++) {
            skip_nodeList.insert_skipnode(skip, i, i + 100);
        }

        skip_nodeList.print_skiplist(skip);

        Skip_Node_List.skip_node start = new Skip_Node_List.skip_node();
        Skip_Node_List.skip_node end = new Skip_Node_List.skip_node();
        skip_nodeList.find_skipnode_inrange(skip, 3, 6.3, start, end);
        for (; start.score < end.score; start = start.level[0].forward) {
            System.out.println(String.format("find result score:%f,level:%d,data:%d ",
                    start.score, start.levelnum, start.data));
        }
        skip_nodeList.remove_inrange(skip, 3, 6.3);
        skip_nodeList.print_skiplist(skip);

        start = skip_nodeList.find_skipnode(skip, 9);
        System.out.println(String.format("find result score:%f,level:%d,data:%d", start.score, start.levelnum, start.data));

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
        skip_list skip = skip_nodeList.create_skiplist();

        for (int i = 0; i < 10; i++) {
            skip_nodeList.insert_skipnode(skip, i, i + 100);
        }

        skip_nodeList.print_skiplist_all(skip);




    }


}
