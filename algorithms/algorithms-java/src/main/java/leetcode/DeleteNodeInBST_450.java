package leetcode;

public class DeleteNodeInBST_450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (key < root.val ) {
            //go left
            root.left = deleteNode(root.left, key);
        }else if (key > root.val){
            //go right
            root.right = deleteNode(root.right, key);
        }else{ //eq
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode min = min(root.right);
            min.right = delMin(root.right);
            min.left = root.left;
            return min;
        }
        return root;
    }

    private TreeNode min(TreeNode x){
        if (x == null) return null;
        while(x.left != null) {
            x = x.left;
        }
        return x;
    }

    private TreeNode delMin(TreeNode x){
        if (x.left == null) return x.right;
        x.left = delMin(x.left);
        return x;
    }

}
