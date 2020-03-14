package leetcode;

import java.util.Stack;

public class MinStack_155 {
    /**
     * push -2
     *
     * [MAX_VALUE, -2, 0, -2, -3]
     * min = -3
     */


    Stack<Integer> stack = new Stack<>();
    Integer min = Integer.MAX_VALUE;
    public void push(int x) {
        if (x<=min){
            //x updates the min, so push the old min and then push x, (also x become the new min)
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int p = stack.pop();
        //now eq, means we have current as min, indicates a change introduced by the top element
        //so previous one in the stack must be the previous min (history)
        if (p == (min)){
            //old min become the new min
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
   }

    public int getMin() {
        return min;
    }
}
