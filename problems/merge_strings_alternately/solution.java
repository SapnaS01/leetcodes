class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int  n = word2.length();
        String ans="";
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) ans += word1.charAt(i);
            if (i < n) ans += word2.charAt(i);
        }
        return ans;
    }
}