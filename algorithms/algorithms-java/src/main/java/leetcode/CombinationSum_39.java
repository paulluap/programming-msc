package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum_39 {

    public static void main(String[] args) {
        CombinationSum_39 sol =  new CombinationSum_39();
        System.out.println(sol.combinationSum(new int[]{2,3,5}, 8));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumRecurse(candidates, 0, target, new LinkedList<Integer>(), result);
        return result;
    }

    public void combinationSumRecurse(int[] candidates, int sum, int target, List<Integer> partial, List<List<Integer>> result) {
        if (sum == target){
            result.add(new ArrayList<>(partial));
            return;
        }
        if (sum > target) return;
        for(int i=0; i<candidates.length; i++) {
            int c = candidates[i];
            //elements in any resulting combination is in ascending order,
            //so that duplicate combinations (with different orders) are avoided
            if (partial.size()>0 && partial.get(partial.size()-1) > c ){
                continue;
            }
            partial.add(c);
            combinationSumRecurse(candidates, sum + c, target, partial, result);
            partial.remove(partial.size()-1);
        }
    }

}
