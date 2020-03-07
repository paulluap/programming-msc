package leetcode;

public class SortList_148 {
    public static void main(String[] args) {
        ListNode list = new ListNode(5);
        list.next(4).next(3).next(1).next(2);
        SortList_148 sol = new SortList_148();
        ListNode head = sol.sortList(list);
        System.out.println(sol.sortList(head));
    }
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //cut half
        /**
         * 1 -> 2 -> 3 -> 4 -> 5
         *           s         f
         *
         * 1 -> 2 -> 3 -> 4
         *           s      f
         *
         */
        ListNode slow = head, fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        ListNode l = sortList(head);
        ListNode r = sortList(slow);
        return merge(l, r);
    }

    private ListNode merge(ListNode l, ListNode r){
        ListNode head = new ListNode(0);
        ListNode n = head;
        while(l!=null || r!=null){
            if (l==null){
                n.next = r;
                break;
//                r = r.next;
            }else if (r==null){
                n.next = l;
                break;
//                l = l.next;
            }else if (less(l, r)){
                n.next = l;
                l = l.next;
            }else{
                n.next = r;
                r = r.next;
            }
            n = n.next;
        }
        return head.next;

    }

    private boolean less(ListNode n1, ListNode n2){
        return n1.val < n2.val;
    }

}
