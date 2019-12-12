package string;

public class MSD {

    static private int charAt(String a , int d){
        if (d < a.length()) return a.charAt(d);
        else return -1;
    }

    public static void sort(String[] a){
        sort(a, 0, a.length-1, 0, new String[a.length]);
    }

    public static void sort(String[] a, int lo, int hi, int d, String[] aux){
        if (hi<=lo) return;
        //frequency count
        int R = 256;
        //need to accomodate -1, so use R+2
        int[] count = new int[R+2];
        for(int i=lo; i<=hi; i++){
            //so count[least r] is 0
            count[charAt(a[i], d)+2]++;
        }

        //acc
        for(int r=0; r<R+1; r++){
            count[r+1] += count[r];
        }
        //distr
        for(int i=lo; i<=hi; i++){
            aux[count[charAt(a[i], d)+1]++] = a[i];
        }

        //copy back
        for(int i=lo; i<=hi; i++){
            //why not a[i] ?, aux is zero based, lo -> 0
            a[i] = aux[i - lo];
        }

        for(int r=0; r<R; r++){
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
        }

    }
}
