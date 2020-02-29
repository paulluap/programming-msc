package string;

import datastructure.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class StringAlgoTest {
    @Test
    public void keyindexedCountingTest(){
        int[] a = new int[]{2, 3, 3, 4, 1, 3, 4, 3, 1, 2, 2, 1, 2, 4, 3, 4, 4, 2, 3, 4};
        KeyIndexedCounting.keyIndexedCountingSort(a, 5);
        System.out.println(CollectionUtils.toString(a));
    }

    @Test
    public void lsdTest(){
        String[] a = new String[]{
                "4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750",
                "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"
        };
        LSD.sort(a, 7);
        Assert.assertEquals(CollectionUtils.toString(a), "[1ICK750,1ICK750,1OHV845,1OHV845,1OHV845,2IYE230,2RLA629,2RLA629,3ATW723,3CIO720,3CIO720,4JZY524,4PGC938]");
    }

    @Test
    public void msdTest(){
        String[] a = new String[]{
                "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"
        };
        MSD.sort(a);
        Assert.assertEquals( "[are,by,sea,seashells,seashells,sells,sells,she,she,shells,shore,surely,the,the]"
                ,CollectionUtils.toString(a));
    }
    @Test
    public void threeWayQuickSortTest(){
        String[] a = new String[]{
                "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"
        };
        ThreeWayQuick.sort(a);
        Assert.assertEquals( "[are,by,sea,seashells,seashells,sells,sells,she,she,shells,shore,surely,the,the]"
                ,CollectionUtils.toString(a));
    }


    @Test
    public void testRegEx(){
        Assert.assertTrue(new RegularExpressionNFA("(.*(A*B|AC)D.*)").recognize("BD"));
        Assert.assertTrue(new RegularExpressionNFA("(.*(A*B|AC)D.*)").recognize("AAAAABD"));
        Assert.assertTrue(new RegularExpressionNFA("(.*(A*B|AC)D.*)").recognize("ACD"));
        Assert.assertFalse(new RegularExpressionNFA("(.*(A*B|AC)D.*)").recognize("AAACD"));

    }

}
