package string;

public class ThreeWayQuick {
    public static void sort(String[] a){
        sort(a, 0, a.length - 1, 0);

    }
    private static int charAt(String s, int d){
        if (d<s.length()) return s.charAt(d);
        return -1;
    }

    private static void sort(String[] a, int lo, int hi, int d){
        if (hi<=lo) return;
        int key = charAt(a[lo], d);
        int lt = lo, gt = hi;
        int i = lo+1;
        //loop
        while(i<=gt){
            int c = charAt(a[i], d);
            if (c < key) {
                //lt points  to the next index to hold the "less" value
                exch(a, i++, lt++); //we know i is less, so then look at next i
            } else if (c > key) {
                exch(a, i, gt--); //we don't know i is less, look at i in the next iteration
            } else {
                i++;
            }
        }
        //everything to the left of lt is less, and to the right of gt is greater
        sort(a, lo, lt-1, d);
        //don't do anything if end of string -1 is reached
        if (key>=0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);

    }

    private static void exch(String a[], int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
