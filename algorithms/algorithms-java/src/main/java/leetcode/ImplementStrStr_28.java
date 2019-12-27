package leetcode;

public class ImplementStrStr_28 {

    public static void main(String[] args) {
//        System.out.println(new ImplementStrStr_28().strStr("hello", "ll"));
//        System.out.println(new ImplementStrStr_28().strStr("a", "a"));
//        System.out.println(new ImplementStrStr_28().strStr("mississippi", "pi"));
        System.out.println(new ImplementStrStr_28().strStr("mississippi", "sippi"));
    }

    //use boyermoore alg
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;

        int N = haystack.length();
        int M = needle.length();
        if (M > N) return -1;

        int skip ;

        int R = 256;
        int[] right = new int[256];
        for (int i=0; i<R; i++){
            right[i] = -1;
        }
        for(int i=0; i<M; i++){
            right[needle.charAt(i)] = i;
        }
        for(int i=0; i<=N-M; i+=skip){
            skip = 0;
            //j is pattern/needle index
            for(int j=M-1; j>=0; j--){
                if (haystack.charAt(i+j) != needle.charAt(j)){
                    skip = j - right[haystack.charAt(i+j)];
                    if (skip < 1){
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) return i;
        }

        return -1;
    }
}
