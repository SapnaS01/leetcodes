class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        final int MOD = 1_000_000_007;
        
        long[][][] dp = new long[zero + 1][one + 1][2];
        
        
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        
        
        long[][] rowpfx1 = new long[zero + 1][one + 1];
        
       
        long[][] colpfx0 = new long[zero + 1][one + 1];
        
        rowpfx1[0][0] = 1;
        colpfx0[0][0] = 1;
        
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                if (i == 0 && j == 0) continue;
                
                
                if (i > 0) {
                    long sumHi = rowpfx1[i - 1][j];
                    long sumLo = (i - limit - 1 >= 0) ? rowpfx1[i - limit - 1][j] : 0;
                    dp[i][j][0] = (sumHi - sumLo + MOD) % MOD;
                }
                
                
                if (j > 0) {
                    long sumHi = colpfx0[i][j - 1];
                    long sumLo = (j - limit - 1 >= 0) ? colpfx0[i][j - limit - 1] : 0;
                    dp[i][j][1] = (sumHi - sumLo + MOD) % MOD;
                }
                
    
                rowpfx1[i][j] = ((i > 0 ? rowpfx1[i - 1][j] : 0) + dp[i][j][1]) % MOD;
                

                colpfx0[i][j] = ((j > 0 ? colpfx0[i][j - 1] : 0) + dp[i][j][0]) % MOD;
            }
        }
        
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}