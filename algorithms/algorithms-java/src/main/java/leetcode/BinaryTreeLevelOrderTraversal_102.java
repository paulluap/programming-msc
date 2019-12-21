package leetcode;

import sun.jvm.hotspot.oops.Array;

import java.util.*;

public class BinaryTreeLevelOrderTraversal_102 {
    public static void main(String[] args) {
        /**
         *    3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         *
         */
        TreeNode root = new TreeNode(3);
        root.left= new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new BinaryTreeLevelOrderTraversal_102().levelOrder(root));

    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> distTo = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        q.add(root);
        distTo.put(root, 0);
        while(!q.isEmpty()){
            TreeNode item = q.remove();
            int level = distTo.get(item);
            //level is 0-based
            if (result.size() <= level){
                result.add(new ArrayList<>());
            }
            result.get(level).add(item.val);

            if (item.left != null) {
                distTo.put(item.left, level+1);
                q.add(item.left);
            }

            if (item.right != null) {
                distTo.put(item.right, level+1);
                q.add(item.right);
            }
        }

        return result;
    }
}
