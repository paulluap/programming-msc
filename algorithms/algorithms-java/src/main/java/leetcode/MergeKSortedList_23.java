package leetcode;

import java.util.List;

public class MergeKSortedList_23 {

    public static void main(String[] args) {
        MergeKSortedList_23 solution = new MergeKSortedList_23();
        ListNode l1 = new ListNode(1);
        l1.next(4).next(5);
        ListNode l2 = new ListNode(1);
        l2.next(3).next(4);
        ListNode l3 = new ListNode(2);
        l3.next(6);
        solution.mergeKLists(new ListNode[]{l1, l2, l3});
        System.out.println(l1);
        solution.mergeKLists(new ListNode[]{});

//        [[-10,-9,-9,-3,-1,-1,0],[-5],[4],[-8],[],[-9,-6,-5,-4,-2,2,3],[-3,-3,-2,-1,0]]
        l1 = new ListNode(-10);
        l1.next(-9).next(-9).next(-3).next(-1).next(-1).next(0);
        l2 = new ListNode(-5);
        l3 = new ListNode(4);
        ListNode l4 = new ListNode(-8);
        ListNode l5 = null;
        ListNode l6 = new ListNode(-9);
        l6.next(-6).next(-5).next(-4).next(-2).next(2).next(3);
        ListNode l7 = new ListNode(-3);
        l7.next(-3).next(-2).next(-1).next(0);
        System.out.println(solution.mergeKLists(new ListNode[]{
                l1, l2, l3, l4, l5, l6, l7
        }));
    }


    //use priority queue to do a multi way merge
    static class PriorityQueue {

        //[1 -> n]
        ListNode pq[];
        int n;

        public PriorityQueue(int maxSize){
            pq = new ListNode[maxSize+1];
            n = 0;
        }

        private boolean greater(int n1, int n2){
            return pq[n1].val > pq[n2].val;
        }

        private void insert(ListNode n1 ){
            n++;
            pq[n] = n1;
            swim(n);
        }


        public boolean isEmpty() {
            return n == 0;
        }

        public int size() { return n;}

        public ListNode delMin(){
            ListNode min = pq[1];
            exch(1, n);
            n--;
            sink(1);
            pq[n+1] = null;
            return min;
        }

        private void sink(int i){
            while(2*i<=n){
                int j = 2*i;
                if (j < n && greater(j, j+1)) j++; //get smaller one
                if (!greater(i, j)) break;
                exch(i, j);
                i = j;
            }
        }

        private void swim(int i){
            while(i>1 && greater(i/2, i)){
                exch(i, i/2);
                i = i/2;
            }
        }

        //exchange pq index
        private void exch(int i, int j){
            ListNode temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
        }

    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;

        PriorityQueue pq = new PriorityQueue(lists.length);
        for(int i=0; i<lists.length; i++){
            if (lists[i]!=null)
                pq.insert(lists[i]);
        }
        ListNode head = null;
        ListNode current = null;
        while(!pq.isEmpty()){
            ListNode min = pq.delMin();
            System.out.println(min.val);

            if (head == null) {
                head = min;
                current = head;
            } else {
                current.next = min;
                current = current.next;
            }

            if (min.next != null) pq.insert(min.next);
        }
        return head;
    }

    //performance is quandratic
    public ListNode mergeKListsBruteForce(ListNode[] lists) {

        ListNode head = pruneMin(lists);
        for(ListNode current = head; current != null; ){
            current.next = pruneMin(lists);
            current = current.next;
        }
        return head;

    }

    private ListNode pruneMin(ListNode[] lists){
        ListNode minHead = null;
        int minHeadIdex = -1;
        for(int i=0; i<lists.length; i++){

            if (lists[i] == null) continue;

            if (minHead == null) {
                minHead = lists[i];
                minHeadIdex = i;
                continue;
            }

            if (lists[i].val < minHead.val){
                minHead = lists[i];
                minHeadIdex = i;
            }
        }

        //prune
        if (minHeadIdex != -1){
            lists[minHeadIdex] = lists[minHeadIdex].next;
        }
        return minHead ;
    }
}
