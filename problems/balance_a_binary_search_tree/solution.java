
// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedList = new ArrayList<>();
        inorder(root, sortedList);
        
        return buildBalancedBST(sortedList, 0, sortedList.size() - 1);
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
    
    private TreeNode buildBalancedBST(List<Integer> list, int start, int end) {
        if (start > end) return null;
        
        int mid = start + (end - start) / 2;
        
        TreeNode root = new TreeNode(list.get(mid));
        
        root.left = buildBalancedBST(list, start, mid - 1);
        root.right = buildBalancedBST(list, mid + 1, end);
        
        return root;
    }
}
