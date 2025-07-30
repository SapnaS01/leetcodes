class Solution {
    public int longestSubarray(int[] nums) {
        int max=nums[0];
        // Max val of the array
        for(int i=0;i<nums.length;i++){
            if(max<nums[i]){
                max=nums[i];
            }
        }
        // System.out.println(max);

        // the longest contiguous subarray of max
        int curLen=0;
        int maxLen=0;
        for(int num: nums){
            if(max==num){
                curLen++;
                maxLen= Math.max(maxLen,curLen);
            }
            else{
                curLen=0;
            }
        }

        return maxLen;



    }
}