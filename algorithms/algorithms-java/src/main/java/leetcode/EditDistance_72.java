package leetcode;

/**
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 */
public class EditDistance_72 {
    public static void main(String[] args) {
        EditDistance_72 sol = new EditDistance_72();
        System.out.println(sol.minDistance("intention", "execution"));
        System.out.println(sol.minDistance("horse", "ros"));
        System.out.println(sol.minDistance("mouse", "mouuse"));
    }

    public int minDistance(String word1, String word2) {
        return minDistanceMatrix(word1, word2);
    }

    public int minDistanceRecuse(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();

        char c1 = word1.charAt(word1.length()-1);
        char c2 = word2.charAt(word2.length()-1);
        int replaceCost = c1 == c2 ? 0 : 1;

        int d1 = minDistanceRecuse(word1.substring(0, word1.length()-1), word2) + 1;
        int d2 = minDistanceRecuse(word1, word2.substring(0, word2.length()-1)) + 1;
        int d3 = minDistanceRecuse(word1.substring(0, word1.length()-1), word2.substring(0, word2.length()-1)) + replaceCost;
        return Math.min(
            Math.min(d1, d2),
                d3);

    }

    public int minDistanceMatrix(String word1, String word2){
        //initialize a matrx
        int[][] m = new int[word1.length()+1][word2.length()+1];
        /**
         * [ [ -  w o r d 2 2
         * [ [ w
         * [ [ o
         * [ [ r
         * [ [ d
         * [ [ 1
         *
         */
        //m.length : first demension, m[0].length: second demension

        for(int i=0; i<m.length; i++){ //row, down word 1
            for(int j=0; j<m[i].length; j++) { //column, left to right word2
                if (i==0){
                    m[i][j] = j;
                } else if (j == 0) {
                    m[i][j] = i;
                } else { //i>0, j>0
                    int r = word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1;
                    m[i][j] = Math.min(
                            Math.min( m[i-1][j] + 1, m[i][j-1] + 1),
                            m[i-1][j-1] + r
                    );
                }
            }
        }

        return m[m.length-1][m[0].length-1];
    }
}
