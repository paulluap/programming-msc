package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak_139 {

    public static void main(String[] args) {
        WordBreak_139 sol = new WordBreak_139();
        System.out.println(sol.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(sol.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(sol.wordBreak("aaaaaaa", Arrays.asList("aaaa", "aaa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        //build a mark array to mark positions where start of word could happen
        boolean[] f = new boolean[s.length()+1];
        f[0] = true;
        for(int i=0; i<s.length(); i++){
            if (!f[i]) continue;
            for(int j=i+1; j<=s.length(); j++){
                String word = s.substring(i, j); //maybe substring is too slow
                if (f[i] && wordDict.contains(word)){
                    f[j] = true;
                }
            }
        }
        //['l', 'e', 'e', 't', 'c', 'o', 'd', 'e']
        //[ 1 ,  0 ,  0 ,  0 ,  1,   0,   0,   0,  1]

        return f[s.length()];
    }

}
