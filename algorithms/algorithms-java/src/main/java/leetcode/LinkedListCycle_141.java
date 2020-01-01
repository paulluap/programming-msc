package leetcode;

import java.util.HashSet;

public class LinkedListCycle_141 {
    public boolean hasCycle(ListNode head) {
        HashSet visited = new HashSet();
        for(ListNode x = head; x!=null; x=x.next){
            if (visited.contains(x)) return true;
            visited.add(x);
        }
        return false;
    }
}
