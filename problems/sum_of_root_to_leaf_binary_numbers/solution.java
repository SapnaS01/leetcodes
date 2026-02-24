
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
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;

        int totalSum = 0;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            int currentSum = (pair.getValue() << 1) | node.val;

            if (node.left == null && node.right == null) {
                totalSum += currentSum;
            }

            if (node.right != null)
                stack.push(new Pair<>(node.right, currentSum));

            if (node.left != null)
                stack.push(new Pair<>(node.left, currentSum));
        }

        return totalSum;
    }
}