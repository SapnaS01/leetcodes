class Solution {
//     public int subarrayBitwiseORs(int[] arr) {

//         Set<Integer> orResults = new HashSet<>();

//         for (int i = 0; i < arr.length; i++) {
//             int currentOR = 0;
//             for (int j = i; j < arr.length; j++) {
//                 currentOR |= arr[j];
//                 orResults.add(currentOR);
//             }
//         }
// // Count unique values
//         Set<Integer> uniqueORs = new HashSet<>(orResults);
//         int uniqueCount = uniqueORs.size();
//         return uniqueCount;
//     }
 public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> prev = new HashSet<>();

        for (int num : arr) {
            Set<Integer> curr = new HashSet<>();
            curr.add(num);

            for (int p : prev) {
                curr.add(p | num);
            }

            result.addAll(curr);
            prev = curr;
        }

        return result.size();
    }
}