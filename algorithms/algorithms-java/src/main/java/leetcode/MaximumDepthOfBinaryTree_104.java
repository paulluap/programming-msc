package leetcode;

import javax.management.QueryEval;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaximumDepthOfBinaryTree_104 {
    public static void main(String[] args) {
        MaximumDepthOfBinaryTree_104 sol = new MaximumDepthOfBinaryTree_104();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        int result = sol.maxDepth(root);
        System.out.println(result);
    }
    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int levelSize = q.size();
        int depth = 1;
        while(!q.isEmpty()){
            TreeNode x = q.remove();
            //we have the next element x on the tree, and we already have levelSize = 0,
            //which means last level is done and x is in the new level.
            if(levelSize-- == 0){
                depth ++;
                levelSize = q.size();
            }
            if (x.left != null) { q.add(x.left); }
            if (x.right != null) { q.add(x.right); }
        }
        return depth;
    }
}
