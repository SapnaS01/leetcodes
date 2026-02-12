class Solution {
    public static int rev(int x){
        int rev=0;
        int r=0;
        while(x!=0){
            r=x%10;
            x=x/10;
            rev=rev*10+r;
        }
        return rev;
    }
    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }
        return x==rev(x);
    }
}