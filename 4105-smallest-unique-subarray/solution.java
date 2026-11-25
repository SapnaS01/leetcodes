class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums);

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;

        int[] lcp = new int[n + 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r > 0) {
                int j = sa[r - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) h++;
                lcp[r] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }

        int ans = n;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            int need = Math.max(lcp[r], r + 1 < n ? lcp[r + 1] : 0) + 1;
            if (need <= n - i && need < ans) ans = need;
        }
        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int m = 100002;
        int[] sa = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        int[] cnt = new int[Math.max(m, n) + 1];

        for (int i = 0; i < n; i++) { x[i] = nums[i]; cnt[x[i]]++; }
        for (int i = 1; i < m; i++) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--cnt[x[i]]] = i;

        for (int k = 1; k < n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) y[p++] = i;
            for (int i = 0; i < n; i++) if (sa[i] >= k) y[p++] = sa[i] - k;

            java.util.Arrays.fill(cnt, 0, m, 0);
            for (int i = 0; i < n; i++) cnt[x[y[i]]]++;
            for (int i = 1; i < m; i++) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--cnt[x[y[i]]]] = y[i];

            int[] t = x; x = y; y = t;
            p = 1;
            x[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1], b = sa[i];
                int a2 = a + k < n ? y[a + k] : -1;
                int b2 = b + k < n ? y[b + k] : -1;
                x[b] = (y[a] == y[b] && a2 == b2) ? p - 1 : p++;
            }
            if (p >= n) break;
            m = p;
        }
        return sa;
    }
}
