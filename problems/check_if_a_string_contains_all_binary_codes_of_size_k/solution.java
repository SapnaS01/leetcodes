class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
//  n-k+1 gives all the possible substring of size k; 1<<k gives all the possible unique binary combinations of size k basically 2^k k=3 n=5 
        if (n - k + 1 < (1 << k)) { //3< 8
            return false;
        }
        
        Set<Integer> sn = new HashSet<>();
        int hash = 0;
        int mask = (1 << k) - 1;  
        
        for (int i = 0; i < n; i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            
            if (i >= k - 1) {
                sn.add(hash);

                if (sn.size() == (1 << k)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
