package sorting;

import datastructure.CollectionUtils;

import java.util.Collection;

public class InsertionSort {
    public static void main(String[] args) {
        String[] a = new String[]{ "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        sort(a);
        //[A,E,E,L,M,O,P,R,S,T,X]
        System.out.println(CollectionUtils.toString(a));
    }

    public static <T extends Comparable<T>> void sort(T[] a){
        for(int i=1; i<a.length; i++){
            for(int j=i; j>0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }

    private static <T extends Comparable<T>> boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
