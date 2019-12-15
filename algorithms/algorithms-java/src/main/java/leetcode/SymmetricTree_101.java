package leetcode;

public class SymmetricTree_101 {
    public static void main(String[] args) {

    }
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return isSymmetric(root.left,root.right);
    }

    private boolean isSymmetric(TreeNode ln, TreeNode lr){
        if (ln == null && lr == null) return true;
        if (ln == null || lr == null) return false;
        if (ln.val != lr.val) return false;
        return isSymmetric(ln.left, lr.right) && isSymmetric(ln.right, lr.left);
    }


}
