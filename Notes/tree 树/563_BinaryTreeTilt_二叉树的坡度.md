

# 563_BinaryTreeTilt_二叉树的坡度

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/binary-tree-tilt/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/binary-tree-tilt/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Tree](https://leetcode.com/tag/tree/)**

---

## 📃题目描述

给定一个二叉树，计算整个树的坡度。

一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。

整个树的坡度就是其所有节点的坡度之和。



**样例 1：**

```
输入: 
         1
       /   \
      2     3
输出: 1
解释: 
结点的坡度 2 : 0
结点的坡度 3 : 0
结点的坡度 1 : |2-3| = 1
树的坡度 : 0 + 0 + 1 = 1
```



**注意：**

1. 任何子树的结点的和不会超过 `32` 位整数的范围。
2. 坡度的值不会超过 `32` 位整数的范围。



****

## 🏹🎯解题思路

涉及到树的问题，首先思考是不是考察树的遍历，本题的描述，使用 后序遍历 可以很方便地实现其计算坡度的要求。

最后落到，在树的遍历过程中，需要维护的值为什么，如何去维护？

**要求：**

+ 一个树的节点的坡度定义即为：该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。整个树的坡度就是其所有节点的坡度之和。

那么就按照 后序遍历 的方法，首先声明一个全局变量，维护整棵树的坡度，每次向下递归的时候，从下到上记录子树的坡度。

注意：**该节点左子树的结点之和和右子树结点之和** ，也就是说，在递归返回时，需要返回  `return leftValue + rightValue + root.val` ，这点有点难以理解，需要好好体会，审题。因为当前的 `root.val` 在上层递归中会转为 `left.val` 或者是 `right.val` 。



#### 代码实现

```java
public class Solution1 {
    int tilt = 0;

    public int findTilt(TreeNode root) {
        tiltHelper(root);
        return tilt;
    }

    public int tiltHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = tiltHelper(root.left);
        int rightValue = tiltHelper(root.right);
        tilt += Math.abs(leftValue - rightValue);
        return leftValue + rightValue + root.val;
    }
}
```



#### 复杂度分析

+ 时间复杂度：`O(n)`。
+ 空间复杂度：`O(n)`。最坏情况下，一个完全倾斜的树，此时需要的空间为 `O(n)` ，平均情况下为 `O(log n)` 。



## 💡总结

有前人做过一个总结：

> leetcode 的 tree 类型题目，落到最后，都是在考察 树 的遍历，无非是三种遍历方式的考察。不同的题目，只是在遍历过程中，维护的变量不一样而已。



[144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

[94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

[145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

> 如何使用非递归算法实现 [二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/) ？



[102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

> 借助 队列 实现 [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)。

