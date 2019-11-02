package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSort {

    public static void main(String[] args) {
        String[] arr = (new String[]{"p", "a", "u", "l", "a", "n", "d", "s", "h", "i", "n", "y"});
        sort(arr);
        String result = Arrays.asList(arr).stream().collect(Collectors.joining(","));
        System.out.println(result);
    }

    //passed in hi > lo
    //partitions the array such that a[lo],...,a[j-1] < a[j] < a[j+1],...,a[hi]
    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        T partitionKey = a[lo];
        int i = lo;
        int j = hi+1;

        while(true){

            while(less(a[++i], partitionKey)){
                //here I made a mistake: i>=j, since j = hi + 1, i would be hi+1 and out of bound
                if(i==hi) break;
            }
            while(less(partitionKey, a[--j])){
                if(j==lo) break;
            }
            if (i>=j) break;

            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    //TODO: checkout Effective Java and improve the generic here
    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        //I misses this guard, if only one element, no need to do anything more
        if (hi <= lo) return;
        int p = partition(a, lo, hi);
        sort(a, lo, p-1);
        sort(a, p+1, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] a){
        //TODO: shuffle first
        sort(a,0, a.length-1);
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
