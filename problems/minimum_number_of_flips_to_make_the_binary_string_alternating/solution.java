class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;

        int res = Integer.MAX_VALUE;
        int diff1 = 0, diff2 = 0;

        for (int i = 0; i < doubled.length(); i++) {

            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';

            if (doubled.charAt(i) != expected1) diff1++;
            if (doubled.charAt(i) != expected2) diff2++;

            if (i >= n) {
                char prev = doubled.charAt(i - n);

                char prevExpected1 = ((i - n) % 2 == 0) ? '0' : '1';
                char prevExpected2 = ((i - n) % 2 == 0) ? '1' : '0';

                if (prev != prevExpected1) diff1--;
                if (prev != prevExpected2) diff2--;
            }

            if (i >= n - 1) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}