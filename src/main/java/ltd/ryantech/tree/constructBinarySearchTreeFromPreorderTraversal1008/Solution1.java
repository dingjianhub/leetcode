package ltd.ryantech.tree.constructBinarySearchTreeFromPreorderTraversal1008;

import ltd.ryantech.tree.TreeNode;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.tree.constructBinarySearchTreeFromPreorderTraversal1008
 * @description 先序遍历二叉搜索树构造二叉树
 * @leetcode_CN_url // https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * @leetcode_US_url // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * @hard_level Medium
 * @tag Tree // https://leetcode-cn.com/tag/tree/
 * @create 2020/05/24 16:06
 **/

public class Solution1 {
    // 分治思想
    public TreeNode bstFromPreorder(int[] preorder) {
        int length = preorder.length;
        if (length == 0) {
            return null;
        }
        return buildBST(preorder, 0, length - 1);
    }

    public TreeNode buildBST(int[] preorder, int left, int right) {
        // 递归结束条件
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[left]);
        int pivot = left;
        // 找到“谷底”
        while (pivot + 1 <= right && preorder[pivot + 1] < preorder[left]) {
            pivot++;
        }
        root.left = buildBST(preorder, left + 1, pivot);
        root.right = buildBST(preorder, pivot + 1, right);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = new Solution1().bstFromPreorder(preorder);
    }
}
