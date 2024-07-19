class Solution {
    public String gcdOfStrings(String s1, String s2) {
        if((s1+s2).equals(s2+s1))
		{
				int n1=s1.length();
				int n2=s2.length();
				int p=1,p1=1,g=0;
				for(int i=1,j=1;i<=n1;i++,j++)
				{
					if(j<=n2 && n2%j==0)
					{
						p1=j;
					}
					if(n1%i==0)
					{
						p=i;
					}
					if(p>=p1&&p%p1==0)
					{
						g=p1;
					}
					if(p<=p1&&p%p1==0)
					{
						g=p;
					}
				}
				
				String sub= s1.substring(0,g);
				return sub;
			
		}
		else {
			return "";
		}
    }
}