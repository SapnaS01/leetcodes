class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> robots[a] - robots[b]);

        int[] pos  = new int[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i]  = robots[idx[i]];
            dist[i] = distance[idx[i]];
        }

        int[] sw = walls.clone();
        Arrays.sort(sw);

        int[] wL = new int[n];
        int[] wR = new int[n];

        for (int i = 0; i < n; i++) {
            int leftLo  = pos[i] - dist[i];
            if (i > 0) leftLo = Math.max(leftLo, pos[i - 1] + 1);
            wL[i] = countWalls(sw, leftLo, pos[i]);

            int rightHi = pos[i] + dist[i];
            if (i < n - 1) rightHi = Math.min(rightHi, pos[i + 1] - 1);
            wR[i] = countWalls(sw, pos[i], rightHi);
        }
        int[] ov = new int[n]; 

        for (int i = 1; i < n; i++) {
            // right
            int rLo = pos[i - 1];
            int rHi = Math.min(pos[i - 1] + dist[i - 1], pos[i] - 1);

            // left
            int lLo = Math.max(pos[i] - dist[i], pos[i - 1] + 1);
            int lHi = pos[i];
            int overlapLo = Math.max(rLo, lLo);
            int overlapHi = Math.min(rHi, lHi);
            ov[i] = countWalls(sw, overlapLo, overlapHi);
        }

        int dpLeft  = wL[0];
        int dpRight = wR[0];

        for (int i = 1; i < n; i++) {
            int newLeft  = Math.max(dpLeft + wL[i],
                                    dpRight + wL[i] - ov[i]);
            int newRight = Math.max(dpLeft, dpRight) + wR[i];
            dpLeft  = newLeft;
            dpRight = newRight;
        }

        return Math.max(dpLeft, dpRight);
    }

    private int countWalls(int[] sortedArr, int lo, int hi) {
        if (lo > hi) return 0;
        return upperBound(sortedArr, hi) - lowerBound(sortedArr, lo);
    }

    private int lowerBound(int[] arr, int val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < val) lo = mid + 1;
            else                hi = mid;
        }
        return lo;
    }

    private int upperBound(int[] arr, int val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= val) lo = mid + 1;
            else                 hi = mid;
        }
        return lo;
    }
}