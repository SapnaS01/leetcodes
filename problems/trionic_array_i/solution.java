class Solution {
   public  boolean isTrionic(int[] nums) {
    int n = nums.length;
    if (n < 4) return false;

    boolean[] incr = new boolean[n];
    boolean[] incrFrom = new boolean[n];

    incr[0] = true;
    for (int i = 1; i < n; i++) {
        if (nums[i] > nums[i - 1] && incr[i - 1]) {
            incr[i] = true;
        } else {
            break;
        }
    }

    incrFrom[n - 1] = true;
    for (int i = n - 2; i >= 0; i--) {
        if (nums[i] < nums[i + 1] && incrFrom[i + 1]) {
            incrFrom[i] = true;
        } else {
            incrFrom[i] = false;
        }
    }

    for (int p = 1; p < n - 2; p++) {
        if (!incr[p]) continue;

        for (int q = p + 1; q < n - 1; q++) {
            boolean isDecreasing = true;
            for (int i = p + 1; i <= q; i++) {
                if (nums[i] >= nums[i - 1]) {
                    isDecreasing = false;
                    break;
                }
            }

            if (isDecreasing && incrFrom[q]) {
                return true;
            }
        }
    }

    return false;
    }

}