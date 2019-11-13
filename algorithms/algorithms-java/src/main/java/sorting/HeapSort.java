package sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HeapSort {

    public static void main(String[] args) {
        String[] arr = (new String[]{"p", "a", "u", "l", "a", "n", "d", "s", "h", "i", "n", "y"});
        sort(arr);
        String result = Arrays.asList(arr).stream().collect(Collectors.joining(","));
        //[a,a,d,h,i,l,n,n,p,s,u,y]
        System.out.println(result);
    }

    public static <T extends Comparable<T>>  void sort(T[] a){
        //pretend a is the same heap used in MaxPQ, where a[0] is left empty, and a starts at a[1], and ends at a[len]
        for (int i = a.length / 2; i>0; i--){
            //use the actual index
            sink(a, i, a.length);
        }
        //we have heap ordered array, then keep moving the largest key at a[0] to the end
        for (int n=a.length; n>1;){
            exch(a, 1, n);
            sink(a, 1, --n);
        }

    }

    private static <T extends Comparable<T>> void sink(T[] a, int i, int heapSize) {
        while(i*2<=heapSize){
            int j = i*2;
            if (j<heapSize && less(a, j, j+1)) j++;
            if (!less(a, i, j)) break;
            exch(a, i, j);
            i = j;
        }
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T temp = a[realIndex(i)];
        a[realIndex(i)] = a[realIndex(j)];
        a[realIndex(j)] = temp;
    }


    private static <T extends Comparable<T>> boolean less(T[] a, int i, int j) {
        return a[realIndex(i)].compareTo(a[realIndex(j)]) < 0;
    }

    //Indices are "off-by-one" to support 1-based indexing.
    private static int realIndex (int heapIndex){
        return heapIndex - 1;
    }

}
