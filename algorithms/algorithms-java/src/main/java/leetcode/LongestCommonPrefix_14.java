package leetcode;

public class LongestCommonPrefix_14 {

    public static void main(String[] args) {
        LongestCommonPrefix_14 sol = new LongestCommonPrefix_14();
        System.out.println(sol.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"a"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"c", "c"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"cc", "cc"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"a", "b"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";
        int c=0;
        for(; c < strs[0].length(); c++ ){
            char x = strs[0].charAt(c);
            for(int r=1; r<strs.length; r++){
                if (c > strs[r].length()-1 || strs[r].charAt(c) != x) {
                    return strs[0].substring(0, c);
                }
            }
        }
        return strs[0].substring(0, c);
    }

}
