package leetcode;

public class MergeTwoSortedList_21 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            ListNode n = this;
            StringBuilder sb = new StringBuilder();
            sb.append(this.val);
            while(n.next != null){
                sb.append("->" + n.next.val);
                n = n.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        //Input: 1->2->4, 1->3->4
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n4;

        ListNode m1 = new ListNode(1);
        ListNode m3 = new ListNode(3);
        ListNode m4 = new ListNode(4);
        m1.next = m3;
        m3.next = m4;
        System.out.println(new MergeTwoSortedList_21().mergeTwoLists(n1, m1));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode current = preHead;
        while(l1 != null || l2 != null){
            if (l1 == null)           { current.next = l2; l2 = l2.next; }
            else if (l2 == null)      { current.next = l1; l1 = l1.next; }
            else if (l1.val < l2.val) { current.next = l1; l1 = l1.next; }
            else                      { current.next = l2; l2 = l2.next; }
            current = current.next;
        }
        return preHead.next;
    }
}
