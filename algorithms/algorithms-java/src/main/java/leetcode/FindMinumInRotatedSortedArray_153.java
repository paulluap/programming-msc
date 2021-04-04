package leetcode;

public class FindMinumInRotatedSortedArray_153 {
    public static void main(String[] args){
        FindMinumInRotatedSortedArray_153 solution = new FindMinumInRotatedSortedArray_153();
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 8, 0, 1}));
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 8, 9, 0}));
        System.out.println(solution.findMin(new int[]{5, 6, 0, 1, 2, 3, 4}));
        System.out.println(solution.findMin(new int[]{6, 0, 1, 2, 3, 4, 5}));
        System.out.println(solution.findMin(new int[]{0, 1, 2, 3, 4, 5, 6}));
        System.out.println(solution.findMin(new int[]{0,1}));


        //[8,0,1]
        //
    }
    /**
     * problem is find the inflection point
     * [4,5,6,7,0,1,2]
     */
    public int findMin(int[] nums) {
        int lo=0;
        int hi=nums.length - 1;

        //now let's find the inflection point
        while(lo <= hi){

            if (lo == hi){
                return nums[lo];
            }

            int mid = lo + (hi - lo) / 2;

            //this comes first in case index is out of bound
            if (nums[mid] > nums[mid+1]){
                return nums[mid+1]; //the inflection point
            }
            if (mid > 0 && nums[mid-1] > nums[mid]){
                return nums[mid]; //the inflection point
            }

            if (nums[mid] > nums[hi]) {
                //inflection point on the right
                lo = mid + 1;
            }

            else {
                //inflection point on the left,  or actually sorted
                hi = mid - 1;
            }

        }

        //end up here if the arraysy is already fully sorted (hi is always decreased until it hi and low crosses)
        return nums[lo];

    }


}
