class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if (m > n) return 0;
        
        long[] dp = new long[m + 1];
        dp[0] = 1L;
        
        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            // iterate j backwards so dp[j-1] refers to the prev state
            for (int j = m; j >= 1; j--) {
                char tc = t.charAt(j - 1);
                if (sc == tc) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return (int) dp[m];
    }
}
