package leetcode;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x){
        val = x;
    }
    public ListNode next(int x){
        this.next = new ListNode(x);
        return next;
    }
    @Override
    public String toString() {
        ListNode n = this;
        StringBuilder sb = new StringBuilder();
        sb.append(n.val);
        while(n.next != null){
            sb.append("->" + n.next.val);
            n = n.next;
        }
        return sb.toString();
    }
}
