class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];   // dp[c] = count of distinct subsequences ending in char c
        long total = 0;             // sum of dp[] = total distinct non-empty subsequences so far
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            
            long newCount = (total + 1) % MOD;   // total ways to extend + itself alone
            
            // remove old contri of this char, add new contri
            total = (total - dp[c] + newCount + MOD) % MOD;
            
            dp[c] = newCount;
        }
        
        return (int) (total % MOD);
    }
}
