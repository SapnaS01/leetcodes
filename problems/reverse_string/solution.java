class Solution {
    public void reverseString(char[] s) {
        char[] s1=new char[s.length];
        int c=0;
        for(int i=s.length-1;i>=0;i--)
        {
            s1[c]=s[i];
            c++;
        }
        for(int i=0;i<s.length;i++)
        {
            s[i]=s1[i];
        }
    }
}