class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        long[] dp = new long[k]; 
        
        for (int i = 0; i < n; i++) {
            long v = nums[i] % k;
            long[] newDp = new long[k];
            
            for (int r = 0; r < k; r++) {
                if (dp[r] != 0) {
                    int newRem = (int) ((r * v) % k);
                    newDp[newRem] += dp[r];
                }
            }
            
            int selfRem = (int) (v % k);
            newDp[selfRem] += 1;
            
            dp = newDp;
            
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}