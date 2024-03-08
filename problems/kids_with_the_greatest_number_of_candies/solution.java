class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n= candies.length;
        List<Boolean> l1=new ArrayList<Boolean>();
        for(int i=0;i<n;i++)
        {
           int l= candies[i]+extraCandies;
           int max=candies[0];
            for(int j=0;j<n;j++)
            {
                if(max<candies[j])
                {
                    max=candies[j];
                }
            }
            if(max<=l)
            {
                l1.add(true);
            }
            else{
                l1.add(false);
            }
        }
        return l1;
        
    }
}