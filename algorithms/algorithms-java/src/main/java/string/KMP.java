package string;

public class KMP {
    final static int R = 256;
    public static int search(String s, String pat){
        int N = s.length();
        int M = pat.length();
        int[][] dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        int X = 0;
        for(int j=1; j<M; j++){
            for(int c=0; c<R; c++){
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j+1;
            X = dfa[pat.charAt(j)][X];
        }

        int i, j;
        for(i=0, j=0; i<N && j<M; i++){
            j = dfa[s.charAt(i)][j];
        }
        if (j==M) return i - M;
        return N;
    }

    public static void main(String[] args) {
        int x = KMP.search("findneedleinheystack", "needle");
        System.out.println(x == 4);
    }
}
