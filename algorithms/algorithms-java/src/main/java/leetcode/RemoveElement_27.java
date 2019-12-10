package leetcode;

public class RemoveElement_27 {

    public static void main(String[] args) {
        int[] a = new int[]{2};
        int len = new RemoveElement_27().removeElement(a, 2);
        for(int i=0; i<len; i++){
            System.out.println(a[i]);
        }
    }

    public int removeElement(int[] nums, int val) {
        int i=0, j=nums.length-1;
        while(j>=0 && i<nums.length && i<=j){
            if (nums[j] == val) { j--; continue; }
            if (nums[i] == val) {
                exch(nums, i, j);
            }
            i++;
        }
        return j+1;
    }

    private void exch(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
