import java.util.*;

// class Solution {
//     public int[] sortArray(int[] n) {
//         for (int i = 0; i < n.length; i++) {
//             for (int j = i + 1; j < n.length; j++) {
//                 if (n[i] > n[j]) {         
//                     int s = n[i];
//                     n[i] = n[j];
//                     n[j] = s;
//                 }
//             }
//         }
//         return n;
//     }

//     public List<Integer> targetIndices(int[] nums, int target) {
//         List<Integer> result = new ArrayList<>();
//         nums = sortArray(nums);
//         for (int i = 0; i < nums.length; i++) {
//             if (target == nums[i]) {
//                 result.add(i);
//             }
//         }
//         return result;
//     }
// }

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        for (int x : nums) {
            if (x < target) less++;
            else if (x == target) equal++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < equal; i++) {
            result.add(less + i);
        }
        return result;
    }
}
