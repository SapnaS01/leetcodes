class Solution {
    public String reverseVowels(String s) {
        String s1="";
        
        for(int i=s.length()-1;i>=0;i--)
        {
            if(s.charAt(i)=='a' ||s.charAt(i)=='e'|| s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u' ||s.charAt(i)=='A'||s.             charAt(i)=='E'||s.charAt(i)=='I'||s.charAt(i)=='O'||s.charAt(i)=='U')
            {
                s1=s1+s.charAt(i);
            }
        }
        int c=0;
        String s2="";
        for(int i=0;i<s.length();i++)
        {
             if(s.charAt(i)=='a' ||s.charAt(i)=='e'|| s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u' ||s.charAt(i)=='A'||s.             charAt(i)=='E'||s.charAt(i)=='I'||s.charAt(i)=='O'||s.charAt(i)=='U')
            {
                s2=s2+s1.charAt(c);
                c++;
            }
            else{
                s2=s2+s.charAt(i);
            }
        }
        return s2;
    }
}