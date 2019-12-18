package leetcode;

import datastructure.CollectionUtils;

public class MergeSortedArray_88 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        new MergeSortedArray_88().merge(nums1, 3, nums2, 3);
        System.out.println(CollectionUtils.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] aux = new int[m+n];

        for (int i=0, j=0, k=0; k<m+n; k++){
            if (i>=m) {
                aux[k] = nums2[j++];
            }
            else if (j>=n) {
                aux[k] = nums1[i++];
            }
            else if (nums1[i] < nums2[j]) {
                aux[k] = nums1[i++];
            }
            else {
                aux[k] = nums2[j++];
            }
        }

        for (int i=0; i<m+n; i++) nums1[i] = aux[i];
    }
}
