package leetcode;

import java.util.HashSet;

//TODO: to improve
public class LinkedListCycle2_142 {
    public ListNode detectCycle(ListNode head) {
        HashSet visited = new HashSet();
        for(ListNode x = head; x!=null; x = x.next){
            if (visited.contains(x)){
                return x;
            }
            visited.add(x);
        }
        return null;
    }
}
