class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1;

        int[] freq = new int[3];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // Case 1
        int run = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) ans = Math.max(ans, ++run);
            else run = 1;
        }

        // Case 2
        int[][] pairIdx = {{0,1,2},{0,2,1},{1,2,0}};
        for (int[] trio : pairIdx) {
            int i1 = trio[0], i2 = trio[1], iex = trio[2];
            if (freq[i1] == 0 || freq[i2] == 0) continue;

            char c1 = (char)('a' + i1), ex = (char)('a' + iex);

            
            int[] storedIndex = new int[2 * n + 1];
            int[] storedGen   = new int[2 * n + 1];

            int gen = 1;
            storedIndex[n] = -1;
            storedGen[n]   = 1;

            int diff = 0;
            boolean has1 = false, has2 = false;

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (ch == ex) {
                    gen++;
                    diff = 0;
                    has1 = false;
                    has2 = false;
                    storedIndex[n] = i;
                    storedGen[n]   = gen;
                } else {
                    if (ch == c1) { diff++; has1 = true; }
                    else          { diff--; has2 = true; }

                    int idx = diff + n;
                    if (storedGen[idx] == gen) {
                        if (has1 && has2) {
                            ans = Math.max(ans, i - storedIndex[idx]);
                        }
                    } else {
                        storedIndex[idx] = i;
                        storedGen[idx]   = gen;
                    }
                }
            }
        }

        // Case 3
        if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
            Map<Long, Integer> seen = new HashMap<>();
            int offset = 2 * n + 1;
            seen.put((long) n * offset + n, -1);

            int ca = 0, cb = 0, cc = 0;
            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if      (ch == 'a') ca++;
                else if (ch == 'b') cb++;
                else                cc++;

                long key = (long)(cb - ca + n) * offset + (cc - ca + n);
                if (seen.containsKey(key)) {
                    if (ca > 0 && cb > 0 && cc > 0)
                        ans = Math.max(ans, i - seen.get(key));
                } else {
                    seen.put(key, i);
                }
            }
        }

        return ans;
    }
}