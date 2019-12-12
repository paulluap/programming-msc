package string;

//least significant digit first sorting
public class LSD {
    /**
     * sort a on leading w characters
     */
    static public void sort(String a[], int w){
        //index counting sort on one particular key
        //key:
        //a[0].charAt(8);
        String aux[] = new String[a.length];
        Object[] b =  a;
        int R = 256;
        for(int d = w-1; d>=0; d--){
            int[] count = new int[R+1];
            //frequency count
            for(int i=0; i<a.length; i++){
                count[a[i].charAt(d)+1] ++;
            }
            //cumulate
            for(int r=0; r<R; r++){
                count[r+1] += count[r];
            }

            //distribute
            for(int i=0; i<a.length; i++){
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            //copy back
            for(int i=0; i<a.length; i++){
                a[i] = aux[i];
            }
        }

    }

}
