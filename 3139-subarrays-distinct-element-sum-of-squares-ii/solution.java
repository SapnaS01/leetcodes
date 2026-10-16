import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;   
    private long[] sq;  
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100_001];
        Arrays.fill(last, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int prev = last[nums[j]];
            update(1, 0, n - 1, prev + 1, j, 1);
            last[nums[j]] = j;
           
            ans = (ans + sq[1]) % MOD;
        }
        return (int) ans;
    }

    private void apply(int node, int len, long v) {
       
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD + (long) len * v % MOD * v) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void pushDown(int node, int lo, int hi) {
        if (lazy[node] != 0) {
            int mid = (lo + hi) >>> 1;
            apply(node << 1, mid - lo + 1, lazy[node]);
            apply(node << 1 | 1, hi - mid, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int lo, int hi, int l, int r, long v) {
        if (l > hi || r < lo) return;
        if (l <= lo && hi <= r) {
            apply(node, hi - lo + 1, v);
            return;
        }
        pushDown(node, lo, hi);
        int mid = (lo + hi) >>> 1;
        update(node << 1, lo, mid, l, r, v);
        update(node << 1 | 1, mid + 1, hi, l, r, v);
        sum[node] = (sum[node << 1] + sum[node << 1 | 1]) % MOD;
        sq[node] = (sq[node << 1] + sq[node << 1 | 1]) % MOD;
    }
}
