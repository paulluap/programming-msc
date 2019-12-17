package leetcode;

public class SortedArrayTwoSum_167 {
    public int[] twoSum(int[] numbers, int target) {
        for(int i=0, j=numbers.length-1; i<j; ){
            int s = numbers[i] + numbers[j];
            if (s>target)      j--;
            else if (s<target) i++;
            else               return new int[]{i+1, j+1};
        }
        return new int[]{};
    }
}
