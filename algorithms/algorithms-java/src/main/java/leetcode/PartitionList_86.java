package leetcode;

//100% runtime, 100% memory
public class PartitionList_86 {

    public static void main(String[] args) {
        // 1->4->3->2->5->2
        ListNode root = new ListNode(1);
        root.next(4).next(3).next(2).next(5).next(2);
        PartitionList_86 sol = new PartitionList_86();
        //1->2->2->4->3->5
        System.out.println(sol.partition(root, 3));

    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftStart = null, rightStart = null;
        ListNode leftEnd = null, rightEnd = null;
        for(ListNode current = head; current != null; ){

            if (current.val < x) {
                if (leftStart == null) {
                    leftStart = current;
                } else {
                    leftEnd.next = current;
                }
                leftEnd = current;
            }else {
                if (rightStart == null) {
                    rightStart = current;
                } else {
                    rightEnd.next = current;
                }
                rightEnd = current;
            }
            ListNode next = current.next;
            current.next = null;
            current = next;
        }
        if (leftStart != null && rightStart != null){
            leftEnd.next = rightStart;
        }

        if (leftStart!=null) return leftStart;
        if (rightStart != null) return rightStart;

        return null;

    }
}
