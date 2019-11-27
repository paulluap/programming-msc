package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 solution = new LongestPalindromicSubstring_5();
        System.out.println(solution.longestPalindrome("babad").equals("bab")); //bab
        System.out.println(solution.longestPalindrome("cbbc").equals("cbbc"));
        System.out.println(solution.longestPalindrome("aisd;fasdfi abacabde").equals("bacab")); //bacab
        System.out.println(solution.longestPalindrome("a").equals("a")); //a
        System.out.println(solution.longestPalindrome("bb").equals("bb")); //bb
        System.out.println(solution.longestPalindrome("bbb").equals("bbb")); //bbb
        System.out.println(solution.longestPalindrome("ac").equals("a")); //a
        System.out.println(solution.longestPalindrome("abb").equals("bb")); //bb
        System.out.println(solution.longestPalindrome("aab832").equals("aa")); //aa
        System.out.println(solution.longestPalindrome("aaaa").equals("aaaa")); //aa
    }
    //babad -> aba or bab
    //cbbd -> bb
    //abacabde -> bacab
    private String candidate;

    public String longestPalindrome(String s) {
        if (s.length()<2) return s;
        if (s.length()==2) return s.charAt(0) == s.charAt(1) ? s : s.substring(0,1);

        candidate = s.substring(0,1);

        //length >= 3
        for(int i=0; i<s.length(); i++){
            if (i<2) continue;
            tryExpand(s, i-2, i);
            tryExpand(s, i-2, i-1);
            tryExpand(s, i-1, i);
        }

        return candidate;
    }

    //returns start and end index
    private void tryExpand(String s, int i, int j) {
        if (charAt(s, i) != charAt(s, j)) return;
        while(i>=0 && j<s.length()) {
            if (charAt(s, i) != charAt(s, j)){
                break;
            }
            i--;
            j++;
        }
        //recover
        i++; j--;

        if ((j- i+1) > candidate.length()){
            candidate = s.substring(i, j+1);
        }
    }

    private Character charAt(String s, int i){
        if (i>=0 && i<s.length()) return s.charAt(i);
        return null;
    }
}
/**
 * Runtime: 12 ms, faster than 60.33% of Java online submissions for Longest Palindromic Substring.
 * Memory Usage: 37.2 MB, less than 95.56% of Java online submissions for Longest Palindromic Substring.
 */
