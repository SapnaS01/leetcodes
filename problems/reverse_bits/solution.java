class Solution {
    public int reverseBits(int n) {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            s.append(n & 1);   
            n = n >> 1;       
        }
        s.reverse();
        int result = 0;
        int power = 1;

        for (int i = 0; i < 32; i++) {
            if (s.charAt(i) == '1') {
                result += power;
            }
            power <<= 1;  
        }

        return result;
    }
}
