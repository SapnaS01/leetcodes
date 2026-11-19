class Solution {
    public int minimizeSum(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) 
        {
            if (x < min1) {
                min3 = min2;
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min3 = min2;
                min2 = x;
            } else if (x < min3) {
                min3 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        int a = max1 - min3;
        int b = max2 - min2;
        int c = max3 - min1;
        return Math.min(a, Math.min(b, c));
    }
}
