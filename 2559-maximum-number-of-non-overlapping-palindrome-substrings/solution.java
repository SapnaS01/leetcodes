class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k) return 0;

 
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            pal[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2) {
                        pal[i][j] = true;
                    } else {
                        pal[i][j] = pal[i + 1][j - 1];
                    }
                } else {
                    pal[i][j] = false;
                }
            }
        }

    
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; 

            if (i - k >= 0) {
                int start = i - k;
                if (pal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }


            if (i - (k + 1) >= 0) {
                int start = i - (k + 1);
                if (pal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}
