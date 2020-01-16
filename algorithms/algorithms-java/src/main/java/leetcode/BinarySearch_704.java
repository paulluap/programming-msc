package leetcode;

public class BinarySearch_704 {

    public static void main(String[] args) {
        BinarySearch_704 bs = new BinarySearch_704();
        int result = bs.search( new int[]{-1,0,3,5,9,12}, 9);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length-1;
        while(lo <= hi){
            int mid = lo+(hi-lo)/2;
            if (target > nums[mid]){
                lo = mid+1;
            }else if(target < nums[mid]){
                hi = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

}
