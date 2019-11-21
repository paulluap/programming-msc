package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(lengthOfLongestSubstring("bbbbb")); //1
        System.out.println(lengthOfLongestSubstring("pwwkew")); //3
        System.out.println(lengthOfLongestSubstring("abcadj")); //5
        System.out.println(lengthOfLongestSubstring("cdd")); //2
        System.out.println(lengthOfLongestSubstring("abba")); //2
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        //l is the latest accumulated length of the substring without repeating chars in the string interation
        //ll is the largest l possible
        int ll =0, l=0, chopOff=-1;
        for(int i=0; i<s.length(); i++){ //i is useless..
            char c = s.charAt(i);
            if (indexMap.get(c) != null && indexMap.get(c) > chopOff/*ignore what we have chopped off*/) {
                //a,b,c,a -> (a),b,c,a : 3 - 0 = 3
                chopOff = indexMap.get(c);
                ll = Math.max(ll, l);
                //how many accumulate so far, after chopping the beginning useless part
                l = i - chopOff;
            }else{
                l++;
            }
            indexMap.put(c, i);
        }
        ll = Math.max(ll, l);

        return ll;
    }
}
