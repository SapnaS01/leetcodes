import java.util.Arrays;
class Solution {
    public static  int[] sortArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int t=arr[j];
                    arr[j]=arr[i];
                    arr[i]=t;
                }
            }
        }
        return arr;
    }
   
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums); 

        int n = nums.length;
        int left = 0;
        int maxWindow = 1;

        for (int right = 0; right < n; right++) {
            while ((long) nums[right] > (long) nums[left] * k) {
                left++;
            }
            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return n - maxWindow;
    
    }

}