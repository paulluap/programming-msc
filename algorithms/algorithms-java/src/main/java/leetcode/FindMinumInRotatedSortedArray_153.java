package leetcode;

public class FindMinumInRotatedSortedArray_153 {
    public static void main(String[] args){
        System.out.println("hello world");
        FindMinumInRotatedSortedArray_153 solution = new FindMinumInRotatedSortedArray_153();
        int[] input = new int[]{4, 5, 6, 7, 0, 1, 2};
        int min = solution.findMin();
        System.out.println("expected 0, actual : " + min);
    }
    /**
     * problem is find the inflection point
     * [4,5,6,7,0,1,2]
     *
     *
     * 
     */
    public int findMin(int[] nums) {
        int lo=0;
        int hi=nums.length - 1;
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            //fully sorted, not rotated
            if (nums[lo] <= nums[mid] && nums[mid] <= nums[hi]){
                return nums[lo];
            }
            //left not sorted, inflection point in the left
            else if (nums[lo] > nums[mid]){
                //search in the left
                hi = mid - 1;
            }

            else if (nums[hi] < nums[mid]){
                lo = mid + 1;
            }
        }
        throw new IllegalArgumentException("intput must be invalid ");
    }
}
