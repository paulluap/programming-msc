package leetcode;

public class SearchInsertPosition_35 {
    public static void main(String[] args) {
        SearchInsertPosition_35 sol = new SearchInsertPosition_35();
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(sol.searchInsert(new int[]{1,3,5,6}, 0));
    }
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (target > nums[mid]) lo = mid+1;
            else if(target < nums[mid]) hi = mid - 1;
            else return mid;
        }
        return lo;

    }
}
