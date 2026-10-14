class Solution {
    public int reverse(int x) {
        final int MAX_DIV_10 = Integer.MAX_VALUE / 10; 
        final int MIN_DIV_10 = Integer.MIN_VALUE / 10; 

        int reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            if (reversed > MAX_DIV_10 || (reversed == MAX_DIV_10 && digit > 7)) return 0;
            if (reversed < MIN_DIV_10 || (reversed == MIN_DIV_10 && digit < -8)) return 0;

            reversed = reversed * 10 + digit;
        }
        return reversed;
    }
}