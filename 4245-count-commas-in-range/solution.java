class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        long lower = 1, upper = 9;
        int digits = 1;

        while (lower <= n) {
            long rangeEnd = Math.min(upper, n);
            long countInRange = rangeEnd - lower + 1;
            int commasPerNumber = (digits - 1) / 3;

            totalCommas += countInRange * commasPerNumber;

            lower = upper + 1;
            upper = upper * 10 + 9;
            digits++;
        }

        return totalCommas;
    }
}
