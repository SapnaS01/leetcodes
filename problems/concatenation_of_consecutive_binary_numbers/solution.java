class Solution {
    public int concatenatedBinary(int n) {
        long MOD = 1_000_000_007;
        long result = 0;
        int bitLength = 0;

        for (int i = 1; i <= n; i++) {
            
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }

            result = ((result << bitLength) + i) % MOD;
        }

        return (int) result;
    }
}