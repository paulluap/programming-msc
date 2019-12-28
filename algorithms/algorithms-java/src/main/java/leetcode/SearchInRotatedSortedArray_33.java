package leetcode;

public class SearchInRotatedSortedArray_33 {
    /**
     * Input: nums = [4,5,6,7,0,1,2,3, 3.5, 3.6 , 3.7, 3.8], target = 0
     * Output: 4
     */
    public static void main(String[] args) {

        SearchInRotatedSortedArray_33 sol = new SearchInRotatedSortedArray_33();
        System.out.println(sol.search(new int[]{4,5,6,7,0,1,2}, 0));

    }

    public int search(int[] nums, int target) {
        int lo = findRotatePoint(nums);
        return binarySearch(nums, target, lo);
    }


    private int findRotatePoint(int[] nums){
        int lo = 0, hi = nums.length-1;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            //mid in the left subarray, so we only need to look at [mid+1, hi]
            //if we do not use mid instead of mid+1, consider only 2 elements, then mid is forever lo...
            //
            if (nums[mid] > nums[hi]){
                lo = mid+1;
            }else{
                //if mid is less than hi, it is possible mid is smallest, so then look at [lo, mid]
                hi = mid;
            }
        }
        return lo;
    }

    public int binarySearch(int[] nums, int key, int bias){
        int lo = 0, hi = nums.length - 1;
        //0,1,2 : mid = 1
        while(lo<=hi){
            int mid = lo + (hi - lo)/2;
            if (key < nums[applyBias(mid, bias, nums.length)]) {
                hi = mid - 1;
            }else if(key > nums[applyBias(mid, bias, nums.length)]) {
                lo = mid + 1;
            }else{
                return applyBias(mid,bias, nums.length);
            }
        }
        return -1;
    }

    private int applyBias(int index, int bias, int N) {
        return (index + bias)%N;
    }


}
