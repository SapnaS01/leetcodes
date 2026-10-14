class Solution {
    public String maxSumOfSquares(int num, int sum) {
        
        if (sum > 9 * num) return "";

        char[] res = new char[num];
        int nines = sum / 9;
        int rem = sum % 9;

        int i = 0;
        for (; i < nines; i++) res[i] = '9';
        if (rem > 0) res[i++] = (char) ('0' + rem);
        for (; i < num; i++) res[i] = '0';

        return new String(res);
    }
}