package leetcode;

import java.util.HashSet;

public class LongestConsecutiveSequence_128 {
    public static void main(String[] args) {
        LongestConsecutiveSequence_128 sol = new LongestConsecutiveSequence_128();
        int result = sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(result);
    }
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hset = new HashSet<>();
        int longest = 0;
        for(int n: nums) hset.add(n);
        for(int n : nums){
            //n cannot be the starting one
            if (hset.contains(n-1)) continue;

            int currentNum = n;
            int count = 1;
            while(hset.contains(currentNum+1)){
                currentNum++;
                count++;
            }
            if (count > longest)  longest = count;
        }
        return longest;
    }
}
