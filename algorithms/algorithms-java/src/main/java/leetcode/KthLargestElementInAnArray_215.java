package leetcode;

public class KthLargestElementInAnArray_215 {
    public static void main(String[] args) {
        KthLargestElementInAnArray_215 solution = new KthLargestElementInAnArray_215();
//        int result = solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
//        System.out.println(result);
        int result = solution.findKthLargest(new int[]{1,2,3,4,5,6}, 1);
        System.out.println(result);
    }
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0, hi = nums.length - 1;
        while(lo<hi){
            int j = pratition(nums, lo, hi);
            if (k<j){
                hi = j-1;
            }else if (k>j){
                lo = j+1;
            }else{
                break;
            }
        }
        return nums[k];
    }

    private int pratition(int[] a, int lo, int hi) {
        int pk = a[lo];
        int i = lo;
        int j = hi + 1;
        while(true) {
            //scan right
            while (a[++i] < pk) {
                if (i==hi) break;
            }
            //scan left
            while (a[--j] > pk) {
                if (j==lo) break;
            }
            if (i>=j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int a[], int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
