import java.util.Arrays;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int len = m + n - 1;

        if ((len & 1) == 1 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBal = len / 2;             
        int words = (maxBal + 1 + 63) >>> 6; 

        long[][] dp = new long[n][words];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long[] cur = dp[j];
                boolean open = grid[i][j] == '(';

                if (i == 0 && j == 0) {
                    Arrays.fill(cur, 0L);
                    cur[0] = 1L << 1;
                    continue;
                }

                long[] merged = new long[words];
                if (i > 0) {
                    for (int w = 0; w < words; w++) merged[w] |= cur[w];
                }
                if (j > 0) {
                    long[] left = dp[j - 1];
                    for (int w = 0; w < words; w++) merged[w] |= left[w];
                }

          
                if (open) {
                    shiftLeft1(merged, words);
                } else {
                    shiftRight1(merged, words);
                }

                maskTop(merged, words, maxBal);

                dp[j] = merged;
            }
        }

        return (dp[n - 1][0] & 1L) != 0;
    }

    private void shiftLeft1(long[] a, int words) {
        long carry = 0;
        for (int w = 0; w < words; w++) {
            long next = a[w] >>> 63;
            a[w] = (a[w] << 1) | carry;
            carry = next;
        }
    }

    private void shiftRight1(long[] a, int words) {
        for (int w = 0; w < words; w++) {
            long high = (w + 1 < words) ? (a[w + 1] & 1L) << 63 : 0L;
            a[w] = (a[w] >>> 1) | high;
        }
    }

    private void maskTop(long[] a, int words, int maxBal) {
        int bitsInLast = (maxBal + 1) - ((words - 1) << 6); 
        if (bitsInLast < 64) {
            a[words - 1] &= (1L << bitsInLast) - 1;
        }
    }
}