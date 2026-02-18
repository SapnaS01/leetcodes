class Solution {
    public boolean hasAlternatingBits(int n) {
        int p = n & 1;  
        n = n >> 1;
        System.out.println(p);

        while (n > 0) {
            int curr = n & 1;
            System.out.println(curr);
            if (curr == p) {
                return false;  
            }

            p = curr;
            n = n >> 1;
        }

        return true;
    }
}
