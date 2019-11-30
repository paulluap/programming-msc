package datastructure;

import org.junit.Assert;
import org.junit.Test;

public class MinQPTest {
    @Test
    public void testMinPQ(){
        MinPQ<Character> pq = new MinPQ<>();
        for(char c : "paulandshiny".toCharArray()){
            pq.insert(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char min = pq.delMin();
            sb.append(min);
//            pq.showHeap();
        }
        Assert.assertEquals("aadhilnnpsuy", sb.toString() );

    }
}
