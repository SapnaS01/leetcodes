/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1;   // position of first critical point
        int lastIdx = -1;    // position of last critical point
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int idx = 1; // curr is at index 1 (0-indexed), prev at 0
        
        while (curr.next != null) {
            boolean isMax = curr.val > prev.val && curr.val > curr.next.val;
            boolean isMin = curr.val < prev.val && curr.val < curr.next.val;
            
            if (isMax || isMin) {
                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    minDist = Math.min(minDist, idx - lastIdx);
                }
                lastIdx = idx;
            }
            
            prev = curr;
            curr = curr.next;
            idx++;
        }
        
        if (firstIdx == -1 || firstIdx == lastIdx) {
            return new int[]{-1, -1};
        }
        
        int maxDist = lastIdx - firstIdx;
        return new int[]{minDist, maxDist};
    }
}
