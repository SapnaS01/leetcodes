class Solution {
    static int count(int n)
	{
		int c=0,r=0;
		while(n!=0)
		{
			r=n%2;
			n=n/2;
			if(r==1)
			{
				c++;
			}
			
		}
		return c;
	}
    public int[] countBits(int n) {
        int[] arr=new int[n+1];
        arr[0]=0;
        for(int i=1;i<=n;i++)
        {
           arr[i]=count(i);
        }
        return arr;
        
    }
}