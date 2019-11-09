package leetcode;

import java.util.List;

//https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers_2 {
    static class ListNode {
      int val;
      ListNode next;
      ListNode next(int i) {
          this.next = new ListNode(i);
          return next;
      }
      ListNode(int x) { val = x; }

      @Override
      public String toString() {
          StringBuilder sb = new StringBuilder();
          ListNode current = this;
          while(current != null){
              sb.append(current.val);
              current = current.next;
              if (current!=null) sb.append("->");
          }
          return sb.toString();
      }
    }

    public static void main(String[] args) {
        //(2 -> 4 -> 3) + (5 -> 6 -> 4)
        System.out.println("---");
        ListNode root1 = new ListNode(2);
        root1.next(4).next(3);
        ListNode root2 = new ListNode(5);
        root2.next(6).next(4);

        ListNode result = addTwoNumbers(root1, root2);
        System.out.println(result);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode root = new ListNode(-1); //a place holder

        ListNode current = root;

        int r = 0;
        while(l1 != null || l2 != null || r!=0) {

            int l1val = l1!= null ?  l1.val : 0;
            int l2val = l2!= null ?  l2.val : 0;
            int x = l1val + l2val + r;

//            current = current.next(x%10);
            current.next = new ListNode(x%10);
            current = current.next;


            r = x >= 10 ? 1 : 0; //大于10进1

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }

        return root.next;
    }

    private static boolean hasNext(ListNode node) {
        return node.next != null;
    }
}
