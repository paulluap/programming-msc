package string;

public class BoyerMoore {
    public static int search(String s, String pat){
        int R = 256;
        int M = pat.length();
        int N = s.length();
        int[] right = new int[R];
        //precompute the index of the right most occurance of c
        for(int c=0; c<R; c++){
            right[c] = -1;
        }
        for(int i=0; i<M; i++){
            right[pat.charAt(i)] = i;
        }

        int skip;
        for(int i=0; i<=N-M; i+=skip){
            skip = 0;
            for(int j=M-1; j>=0; j--){
                if (s.charAt(i+j) != pat.charAt(j)){
                    //change skip
                    System.out.println("[i] : " + i + " skip: " + s.charAt(i+j));
                    skip = Math.max(1, j-right[s.charAt(i+j)]);
                    break;
                }
            }
            if (skip==0) return i;
        }

        return N;

    }

    public static void main(String[] args) {
//        int x = BoyerMoore.search("findneedleinheystack", "needle");
        int x = BoyerMoore.search("mississippi", "sippi");
        System.out.println(x );
    }
}
