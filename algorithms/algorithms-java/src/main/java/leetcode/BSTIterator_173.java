package leetcode;


import java.util.Stack;

class BSTIterator_173 {

    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator_173(TreeNode root) {

        for(TreeNode x=root; x!=null; x=x.left){
            stack.push(x);
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode n = stack.pop();
        for(TreeNode x=n.right; x!=null; x=x.left) {
            stack.push(x);
        }
        return n.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}