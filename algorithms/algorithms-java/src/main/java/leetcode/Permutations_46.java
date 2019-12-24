package leetcode;

import java.util.*;

public class Permutations_46 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations_46().permute(new int[]{1,2,3});
        System.out.println(result);

    }

    public List<List<Integer>> permute(int[] nums) {
//        Set<Integer> set = new TreeSet<Integer>();
//        for(int n: nums) set.add(n);
//        return permuteslow(set);

        List<List<Integer>> list = new ArrayList<>();
        permute(list, new ArrayList<>(), nums);
        return list;
    }

    private void permute (List<List<Integer>> list, List<Integer> p, int[] nums){

        if (p.size() == nums.length) {
            list.add(new ArrayList<>(p));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if (p.contains(nums[i])) continue;
            //nums[i] is not present in p, so append nums[i]
            p.add(nums[i]);
            permute(list, p, nums);
            //undo the append, so reuse p
            p.remove(p.size()-1);
        }
    }

    //this is slow
    private List<List<Integer>> permuteslow(Set<Integer> nums){
        if (nums.size() == 1) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            l.add(nums.iterator().next());
            result.add(l);
            return result;
        }

        //partial result
        List<List<Integer>> result = new ArrayList<>();
        for(int i: nums){
            for(List<Integer> p : permuteslow(copyWithout(nums, i))){
                p.add(i);
                result.add(p);
            }
        }
        return result;
    }

    private Set<Integer> copyWithout(Set<Integer> set, int key){
        TreeSet<Integer> newSet = new TreeSet<>(set);
        newSet.remove(key);
        return newSet;
    }


}
