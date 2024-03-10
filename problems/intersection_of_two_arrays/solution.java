class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1= new HashSet<>();
        for(int i=0;i<nums1.length;i++)
        {
            s1.add(nums1[i]);
        }
         Set<Integer> s2= new HashSet<>();
        for(int i=0;i<nums2.length;i++)
        {
            s2.add(nums2[i]);
        }
        Set<Integer> a= new HashSet<>();
       if(s1.size()>=s2.size())
       {
              for(int ss:s2)
               {
                   if(s1.contains(ss))
                   {
                       a.add(ss);
                   }
               }
       }
       else{
              for(int ss:s1)
               {
                   if(s2.contains(ss))
                   {
                       a.add(ss);
                   }
               }

       }
        
     int[] ar=new int[a.size()];
     int c=0;
     for(int ele:a)
     {
         ar[c]=ele;
         c++;
     }
     return ar;
    }
}