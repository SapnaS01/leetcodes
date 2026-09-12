import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        long[] L = new long[n];
        long[] R = new long[n];
        long[] W = new long[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(i);
            L[i] = iv.get(0);
            R[i] = iv.get(1);
            W[i] = iv.get(2);
        }

        // indices sorted by right endpoint ascending; tie-break by original index
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> {
            if (R[a] != R[b]) return Long.compare(R[a], R[b]);
            return Integer.compare(a, b);
        });

        long[] sortedL = new long[n];
        long[] sortedR = new long[n];
        long[] sortedW = new long[n];
        int[] origIndex = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = order[i];
            sortedL[i] = L[idx];
            sortedR[i] = R[idx];
            sortedW[i] = W[idx];
            origIndex[i] = idx;
        }

        // j[i] = count of intervals in sorted[0..i-1] with r < sortedL[i] (binary search)
        int[] j = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i;
            long Li = sortedL[i];
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (sortedR[mid] < Li) lo = mid + 1;
                else hi = mid;
            }
            j[i] = lo;
        }

        final int K = 4;
        long[][] dpScore = new long[K + 1][n + 1];
        int[][][] dpList = new int[K + 1][n + 1][];

        int[] empty = new int[0];
        for (int k = 0; k <= K; k++) dpList[k][0] = empty;
        for (int i = 0; i <= n; i++) dpList[0][i] = empty;

        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= n; i++) {
                // option A: skip interval i
                long scoreA = dpScore[k][i - 1];
                int[] listA = dpList[k][i - 1];

                // option B: take interval i, combine with best non-overlapping prefix
                int c = j[i - 1];
                long scoreB = dpScore[k - 1][c] + sortedW[i - 1];

                if (scoreB > scoreA) {
                    dpScore[k][i] = scoreB;
                    dpList[k][i] = insertSorted(dpList[k - 1][c], origIndex[i - 1]);
                } else if (scoreB == scoreA) {
                    int[] listB = insertSorted(dpList[k - 1][c], origIndex[i - 1]);
                    if (compareLex(listB, listA) < 0) {
                        dpScore[k][i] = scoreB;
                        dpList[k][i] = listB;
                    } else {
                        dpScore[k][i] = scoreA;
                        dpList[k][i] = listA;
                    }
                } else {
                    dpScore[k][i] = scoreA;
                    dpList[k][i] = listA;
                }
            }
        }

        return dpList[K][n];
    }

    // insert v into asc sorted array base, returning a new sorted array (size <= 4, so Oi(1)).
    private int[] insertSorted(int[] base, int v) {
        int[] res = new int[base.length + 1];
        int idx = 0;
        while (idx < base.length && base[idx] < v) {
            res[idx] = base[idx];
            idx++;
        }
        res[idx] = v;
        for (int p = idx; p < base.length; p++) {
            res[p + 1] = base[p];
        }
        return res;
    }

    // lexicographic comparison of two index lists.
    private int compareLex(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return a[i] - b[i];
        }
        return a.length - b.length;
    }
}
