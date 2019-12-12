package string;

public class KeyIndexedCounting {
    static public void keyIndexedCountingSort(int a[], int R){
        int[] count = new int[R+1];
        //frequency count
        for(int i=0; i<a.length; i++){
            count[a[i]+1]++;
        }
        //cumulate
        for(int i=0; i<R; i++){
            count[i+1] += count[i];
        }

        //put in aux in order
        int[] aux = new int[a.length];
        for(int i=0; i<a.length; i++){
            aux[count[a[i]]++] = a[i];
        }

        //copy back
        for(int i=0; i<a.length; i++){
            a[i] = aux[i];
        }

    }
}
