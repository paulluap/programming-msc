package leetcode;

public class RemoveDuplicateInSortedArray_26 {
    public int removeDuplicates(int[] nums) {
        //[1,1,2,3,5,8] -> [1,2,3,5,8]
        int i=0;
        for(int j = 1; j < nums.length ; j++){
            if (nums[i] !=  nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i+1;
    }
}
