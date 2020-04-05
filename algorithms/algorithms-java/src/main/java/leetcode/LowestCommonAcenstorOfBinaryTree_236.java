package leetcode;

public class LowestCommonAcenstorOfBinaryTree_236 {
    public static void main(String[] args) {
        LowestCommonAcenstorOfBinaryTree_236 sol = new LowestCommonAcenstorOfBinaryTree_236();
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode six = new TreeNode(6);
        TreeNode two = new TreeNode(2);
        TreeNode zero = new TreeNode(0);
        TreeNode eight = new TreeNode(8);
        TreeNode seven = new TreeNode(7);
        TreeNode four = new TreeNode(4);
        three.left = five;
        three.right = one;
        five.left = six;
        five.right = two;
        one.left = zero;
        one.right = eight;
        two.left = seven;
        two.right = four;
        TreeNode result = sol.lowestCommonAncestor(three, two, zero);
//        System.out.println(result);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        hasEither(root, p, q);
        return ans;
    }


    private TreeNode ans;
    //returns if x tree contains either p or q
    private boolean hasEither(TreeNode x, TreeNode p, TreeNode q){
        if (x == null){
            return false;
        }
        int self = x==p || x==q ? 1 : 0;
        int left = hasEither(x.left, p, q) ? 1 : 0;
        int right = hasEither(x.right, p, q) ? 1 : 0;
        //p and q should appear in different branches, left, right, or self
        if (self + left + right == 2){
            this.ans = x;
        }
        return self + left + right > 0;
    }

}
