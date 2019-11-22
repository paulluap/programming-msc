package datastructure;

import org.junit.Assert;
import org.junit.Test;

public class BSTTest {

    @Test
    public void showBst() {
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<Integer, Integer>();
        for(int i=0; i<50; i++){
            bst.put(i,i);
        }
        bst.showTree();

    }
    @Test
    public void testBst(){
        BST<String, String> bst = new BST<String, String>();
        Assert.assertEquals(0, bst.size());
        bst.put("P","p");
        bst.put("A","a");
        bst.put("U","u");
        bst.put("L","l");
        bst.put("A","a2");
        bst.put("N","n");
        bst.put("D","d");
        bst.put("S","s");
        bst.put("H","h");
        bst.put("I","i");
        bst.put("N","n2");
        bst.put("Y","y");
        Assert.assertEquals(10, bst.size());
        Assert.assertEquals("s", bst.get("S"));
        Assert.assertEquals("a2", bst.get("A"));
        Assert.assertEquals(null, bst.get("a"));
        bst.showTree();
//        bst.delete("P");
//        bst.delete("A");
//        bst.delete("a");
//        Assert.assertEquals(8, bst.size());
//        Assert.assertNotNull(bst.get("D"));
//        bst.deleteMin();
//        Assert.assertNull(bst.get("D"));
//        Assert.assertEquals(7, bst.size());

    }

}
