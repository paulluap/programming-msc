package leetcode;

public class SwapNodesInPairs_24 {
    public static void main(String[] args) {
        SwapNodesInPairs_24 sol = new SwapNodesInPairs_24();
        ListNode n1 = new ListNode(1);
        n1.next(2).next(3).next(4).next(5);
        System.out.println(n1);
        System.out.println(sol.swapPairs(n1));

    }
    //Given 1->2->3->4, you should return the list as 2->1->4->3.
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode a = head, b = head.next;
        //original : a, a.next is b, b.next
        a.next = swapPairs(b.next);
        b.next = a;
        return b;
    }
}

/**
 Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
 Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Swap Nodes in Pairs. */