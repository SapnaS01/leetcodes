import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int[] last = new int[101];
        Arrays.fill(last, -1);

        int[] d = new int[n];  
        int total = 0;
        int s2 = 0;             

        for (int j = 0; j < n; j++) {
            int x = nums.get(j);
            int p = last[x];
            for (int i = p + 1; i <= j; i++) {
                s2 += 2 * d[i] + 1;  
                d[i]++;
            }
            total += s2;
            last[x] = j;
        }
        return total;
    }
}
