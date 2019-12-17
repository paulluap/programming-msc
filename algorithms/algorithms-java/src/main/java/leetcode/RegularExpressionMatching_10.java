package leetcode;

import java.util.HashSet;
import java.util.Set;

public class RegularExpressionMatching_10 {
    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching_10().isMatch("aa", "a"));//false
        System.out.println(new RegularExpressionMatching_10().isMatch("aa", "aa"));
        System.out.println(new RegularExpressionMatching_10().isMatch("aabbcc", "a.b.c."));
        System.out.println(new RegularExpressionMatching_10().isMatch("aacc", "a.b*c."));
        System.out.println(new RegularExpressionMatching_10().isMatch("aab", "c*a*b"));
        System.out.println(new RegularExpressionMatching_10().isMatch("ab", ".*c")); //false
        System.out.println(new RegularExpressionMatching_10().isMatch("aaa", "ab*a*c*a*")); //true
    }
    //build NFA, do simulation
    public boolean isMatch(String s, String p) {
        int M = p.length();
        char re[] = p.toCharArray();
        Set<Integer> possibleStates = new HashSet<>();
        //initial possible states:
        possibleStates.add(0);
        //take e-transition

        int x = 0;
        if (x < M && re[x] != '*') x++;
        while (x < M && re[x] == '*'){
            possibleStates.add(x+1);
            x = x+2;
        }


        for(int i=0; i<s.length(); i++){
            Set<Integer> match = new HashSet<>();
            for(int x2: possibleStates){
                if (x2<M)
                if (re[x2] == s.charAt(i) || re[x2] == '.'){
                    match.add(x2+1);
                }
            }
            possibleStates = new HashSet<>();
            for(int m: match){
                possibleStates.add(m);

                //if match take e-transition
                if (m < M && re[m] == '*') {
                    possibleStates.add(m-1);
                }

                int x3 = m;
                if (x3 < M && re[x3] != '*') x3++;

                while (x3 < M && re[x3] == '*'){
                    possibleStates.add(x3+1);
                    x3 = x3+2;
                }
            }
        }

        for(int st : possibleStates){
            if (st == M) return true;
        }

        return false;
    }
}
