class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> s1=new HashSet<>();
        for(int i:nums1)
        {
            s1.add(i);
        }      
        for(int i1:nums2) 
        {
            if(s1.contains(i1))
            {
                return i1;
            }
        } 
        // for(int i=0;i<nums1.length;i++)
        // {
        //     for(int j=0;j<nums2.length;j++)
        //    {
        //      if(nums1[i]==nums2[j])
        //      {
        //          return nums1[i];
        //      }
        //    }
        // }
        return -1;
    }
}