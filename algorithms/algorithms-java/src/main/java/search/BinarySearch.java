package search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10};
        for(int i=0; i<a.length; i++){
            if (indexOf(a, i) == (i-1)){
                System.out.println("pass " + a[i]);
            }else{
                System.out.println("fail for search" + a[i]);
            }
        }
    }

    public static int indexOf(int a[], int key){
        int lo = 0, hi = a.length - 1;

        //mid is either the exact middle index or the last of the left half
        //[1,2] mid index: 0 lo=0, hi=1;
        //[1,2,3] mid index: 1, lo=0, hi=2
        //[1]: mid index: 0, hi = 0, lo = 0
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])     hi = mid - 1;
            else if(key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
