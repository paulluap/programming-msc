package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort {

    public static void main(String[] args) {
        String[] arr = (new String[]{"p", "a", "u", "l", "a", "n", "d", "s", "h", "i", "n", "y"});
        sort(arr);
        String result = Arrays.asList(arr).stream().collect(Collectors.joining(","));
        System.out.println(result);

    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        //below [lo,...,hi] has at least 2 elements
        int mid  = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        //copy to aux, and copy back to a
        for(int i=lo; i<=hi; i++){
            aux[i] = a[i];
        }

        int j=lo;
        int k=mid+1;

        for (int i=lo; i<=hi; i++){
            //every loop we increment either j or k,
            //so either j exceeds limit first or k exceeds limit first
            if (j>mid) {
                a[i] = aux[k++];
            }else if (k>hi){
                a[i]=aux[j++];
            }else if (less(aux[j], aux[k])){
                a[i] = aux[j++];
            }else{
                a[i] = aux[k++];
            }
        }

    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

}
