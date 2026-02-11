import java.util.*;

class Solution {
    private int[] minSeg, maxSeg, lazy;
    private int N;

    public int longestBalanced(int[] nums) {
        N = nums.length;
        int size = 4 * (N + 2);
        minSeg = new int[size];
        maxSeg = new int[size];
        lazy   = new int[size];
        
        int[] prev = new int[N];
        int[] lastOcc = new int[100001];
        Arrays.fill(lastOcc, -2);
        for (int i = 0; i < N; i++) {
            prev[i] = lastOcc[nums[i]];
            lastOcc[nums[i]] = i;
        }

        int ans = 0;
        for (int r = 0; r < N; r++) {
            
            int lo = prev[r] + 1;
            int delta = ((nums[r] & 1) == 0) ? 1 : -1;
            rangeAdd(1, 0, N, lo, r, delta);

            
            int pos = findLeftmostZero(1, 0, N, 0, r);
            if (pos != -1) {
                ans = Math.max(ans, r - pos + 1);
            }
        }
        return ans;
    }

    private void pushDown(int node) {
        if (lazy[node] != 0) {
            apply(2 * node,     lazy[node]);
            apply(2 * node + 1, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int val) {
        minSeg[node] += val;
        maxSeg[node] += val;
        lazy[node]   += val;
    }

    private void rangeAdd(int node, int start, int end, int l, int r, int val) {
        if (l > r || r < start || end < l) return;
        if (l <= start && end <= r) {
            apply(node, val);
            return;
        }
        pushDown(node);
        int mid = (start + end) >>> 1;
        rangeAdd(2 * node,     start,   mid, l, r, val);
        rangeAdd(2 * node + 1, mid + 1, end, l, r, val);
        minSeg[node] = Math.min(minSeg[2 * node], minSeg[2 * node + 1]);
        maxSeg[node] = Math.max(maxSeg[2 * node], maxSeg[2 * node + 1]);
    }

    
    private int findLeftmostZero(int node, int start, int end, int ql, int qr) {
        if (ql > qr || qr < start || end < ql) return -1;
        
        if (minSeg[node] > 0 || maxSeg[node] < 0) return -1;
        if (start == end) return start; 
        pushDown(node);
        int mid = (start + end) >>> 1;
        int res = findLeftmostZero(2 * node, start, mid, ql, qr);
        if (res != -1) return res;
        return findLeftmostZero(2 * node + 1, mid + 1, end, ql, qr);
    }
}