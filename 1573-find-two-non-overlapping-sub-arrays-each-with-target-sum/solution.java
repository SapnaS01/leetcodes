class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
 
        int[] dp = new int[n];
        int ans = Integer.MAX_VALUE;
        
        int left = 0;
        int sum = 0;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            dp[right] = (right > 0) ? dp[right - 1] : Integer.MAX_VALUE;
            
            if (sum == target) {
                int curLen = right - left + 1;
                
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, curLen + dp[left - 1]);
                }
                
                dp[right] = Math.min(dp[right], curLen);
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
