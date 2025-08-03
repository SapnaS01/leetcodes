class Solution {
    private boolean canReach(int posLeft, int posRight, int startPos, int k) {
        // Case 1: Go left first, then right
        int leftFirstSteps = Math.abs(startPos - posLeft) + (posRight - posLeft);
        // Case 2: Go right first, then left
        int rightFirstSteps = Math.abs(posRight - startPos) + (posRight - posLeft);

        return leftFirstSteps <= k || rightFirstSteps <= k;
    }

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int left = 0, totalFruits = 0, maxFruits = 0;

        for (int right = 0; right < n; right++) {
            totalFruits += fruits[right][1];

            while (left <= right && !canReach(fruits[left][0], fruits[right][0], startPos, k)) {
                totalFruits -= fruits[left][1];
                left++;
            }

            maxFruits = Math.max(maxFruits, totalFruits);
        }

        return maxFruits;
    }
}
