package leetcode;


public class RemoveNthFromEnd_19 {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        new RemoveNthFromEnd_19().removeNthFromEnd(n5, 1);
        System.out.println(n5);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null){
            return null;
        }
        int size = markRemove(head, n);
        if (size == n){
            return head.next;
        }
        preRemove.next = preRemove.next.next;
        return head;
    }

    private ListNode preRemove;
    private int markRemove(ListNode head, int n){
        //last one
        if (head.next == null) return 1;
        int current = 1 + markRemove(head.next, n);
        if (current == n+1){
            this.preRemove = head;
        }
        return current;
    }
}
