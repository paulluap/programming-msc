package leetcode;

import datastructure.CollectionUtils;

import java.util.Arrays;

public class NextPermutation_31 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        NextPermutation_31 sol = new NextPermutation_31();
        sol.nextPermutation(arr);
        System.out.println(CollectionUtils.toString(arr));

    }

    public void nextPermutation(int[] nums){
        //from the last second one,
        //find an element that can be exchanged with a following element just larger than it
        //at the same time, perform an insertion sort
        for(int i=nums.length-1; i>=0; i--){

            if (nums[nums.length-1] > nums[i]){
                for(int j=i; j<nums.length; j++){
                    //just find the least one > nums[i]
                    if (nums[j] > nums[i]){
                        exchange(nums, i, j);
                        break;
                    }
                }
                break;
            }

            for (int j=i; j<nums.length-1; j++){
                if (nums[j] > nums[j+1]){
                    exchange(nums, j, j+1);
                }
            }
        }

    }

    private void exchange(int[] nums, int i, int j){
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }
}
