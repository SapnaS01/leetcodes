class Solution {
    public int sumOfSquares(int[] nums) {
        final int n = nums.length;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                final int val = nums[i - 1];
                sum += val * val;
            }
        }
        return sum;
    }
}