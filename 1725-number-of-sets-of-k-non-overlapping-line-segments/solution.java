class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;

       
        long[] prev0 = new long[k + 1];
        long[] prev1 = new long[k + 1];
        prev0[0] = 1L; 

        for (int i = 1; i < n; i++) {
            long[] cur0 = new long[k + 1];
            long[] cur1 = new long[k + 1];
            cur0[0] = 1L;

            for (int j = 1; j <= k; j++) {
                cur0[j] = (prev0[j] + prev1[j]) % MOD;

                long start = prev0[j - 1];      
                long shareEndpoint = prev1[j - 1]; 
                long extend = prev1[j];           

                cur1[j] = (extend + start + shareEndpoint) % MOD;
            }

            prev0 = cur0;
            prev1 = cur1;
        }

        return (int) ((prev0[k] + prev1[k]) % MOD);
    }
}
