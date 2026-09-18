import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }
        
        List<int[]> intervals = new ArrayList<>(); 
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] != i) continue; 
            
            int start = i;
            int end = last[c];
            boolean valid = true;
            
            int j = start;
            while (j <= end) {
                int cj = s.charAt(j) - 'a';
                if (first[cj] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[cj]);
                j++;
            }
            
            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }
        
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return (a[1] - a[0]) - (b[1] - b[0]);
        });
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return result;
    }
}
