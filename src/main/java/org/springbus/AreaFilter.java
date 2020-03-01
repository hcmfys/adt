package org.springbus;

import java.util.ArrayList;
import java.util.List;

public class AreaFilter {


    class SelectionNode {
        public int x;
        public int y;
    }
   /*
    # A, B 形如 [[0,2],[5,10]...]
    def intervalIntersection(A, B):
    i, j = 0, 0 # 双指针
    res = []
            while i < len(A) and j < len(B):
    a1, a2 = A[i][0], A[i][1]
    b1, b2 = B[j][0], B[j][1]
            # 两个区间存在交集
        if b2 >= a1 and a2 >= b1:
            # 计算出交集，加入 res
            res.append([max(a1, b1), min(a2, b2)])
            # 指针前进
        if b2 < a2: j += 1
            else:       i += 1
            return res


    */

   public List<SelectionNode> intervalIntersection(SelectionNode[] A, SelectionNode[] B) {
       int i = 0;
       int j = 0;
       List<SelectionNode> res = new ArrayList();
       while (i < A.length && j < B.length) {
           int a1=A[i].x;
           int a2=A[i].y;
           int b1=B[j].x;
           int b2=B[j].y;
           if (b2>=a1 && a2>=b1) {
               SelectionNode node = new SelectionNode();
               node.x = Math.max(a1, b1);
               node.y = Math.min(a2, b2);
               res.add(node);
           }
           if (b2 <a2) {
               j++;
           }else{
               i++;
           }
       }
       return res;
   }



}
