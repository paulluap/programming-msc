package leetcode;

import java.util.Stack;


//TODO: improve me ...

public class ReverseNodesInKGroups_25 {
    public static void main(String[] args) {
        ListNode n = new ListNode(1);
        n.next(2).next(3).next(4).next(5);
        System.out.println(n);

        System.out.println(new ReverseNodesInKGroups_25().reverseKGroup(n, 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode[] headTail = reverse(head, k);
        ListNode head2 = headTail[0], tail = headTail[1], next = headTail[2] ;
//        System.out.println("reversed: " + head2 + " tail: " + tail + ", next: " +next);
        if (next == null) {
            return head2;
        }
        tail.next = reverseKGroup(next, k);
        return head2;
    }

    //1->2->3 => 3->2->1
    private ListNode[] reverse(final ListNode head, int k){
        Stack<ListNode> stack = new Stack<>();
        ListNode myhead = head;
        for(int i=0; i<k; i++){
            if (myhead == null) return new ListNode[]{head, null, null};
            stack.add(myhead);
            //since we fetch one more next, so later myHead is the next node process
            myhead = myhead.next;
        }
        final ListNode next = myhead;

        ListNode newHead = stack.pop();
        ListNode current = newHead;
        while(!stack.isEmpty()){
            current.next = stack.pop();
            current = current.next;
        }
        //otherwise, creates circle
        ListNode oldNext  = current.next;
        current.next = null;
        return new ListNode[]{newHead, current, next};
    }
}
