# 98_ValidateBinarySearchTree_验证搜索二叉树

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/validate-binary-search-tree/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/validate-binary-search-tree/)

📗Difficulty：**Medium** 	

🎯Tags：

+ **[Recursion](https://leetcode.com/tag/recursion/)**
+ **[Tree](https://leetcode.com/tag/tree/)**
+ [depth-first-search](https://leetcode.com/tag/depth-first-search/)

## 📃题目描述：

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

**样例 1：**

```
输入:
    2
   / \
  1   3
输出: true
```



**样例 2：**

```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```

****

## 🏹🎯解题思路

### 中序遍历 递归

```java
class Solution {
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true; // 注意此处条件
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点，如果当前节点的值小于等于中序遍历的前一个节点值，不满足二叉搜索树特性（BST），返 false
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
}
```

#### 复杂度分析

+ 时间复杂度：O(n)。二叉树中每个节点最多被访问 1 次。
+ 空间复杂度：



### 中序遍历 非递归版

```java
public boolean isValidBST(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    long inorderValue = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        if (root.val <= inorderValue) {
            return false;
        }
        inorderValue = root.val;
        root = root.right;
    }
    return true;
}
```

#### 举例：

##### 当树为样例 1 时：

+ 首先进入第一层 `while` 循环，进入第二层 `while` 循环，依次压入节点 `2，1`，随后弹栈，root 为节点 1。第一个被访问到的元素肯定不小于 `inorderValue` 的最小值，将 `inorderValue` 的值更新为当前 root 的 val，即 1。更新 root 为其右子树节点。
+ 栈不空，此时 root 为 null。弹栈，root 此时为 2。即原本的根节点。其值不小于 `inorderValue` 的值，更新 `inorderValue` 值为 2。root 更新为其右子树节点，即节点值为 3 的节点。
+ 栈空，当 root 不为 null。进入第二层 `while` 循环，压栈，此时栈中有元素 3 的节点。弹栈，root 此时为节点值 3 的节点。其不小于 `inorderValue` 的值 2 ，更新 `inorderValue` 值为 3。root 更新为节点 3 的右子树。**此时，栈空，root 值为 null。**
+ 第一层 `while` 循环结束，返回 `true`。

##### 当树为样例 2 时：

+ 首先进入第一层 `while` 循环，进入第二层 `while` 循环，依次压入节点 `5，1`，随后弹栈，root 为节点 1。第一个被访问到的元素肯定不小于 `inorderValue` 的最小值，将 `inorderValue` 的值更新为当前 root 的 val，即 1。更新 root 为其右子树节点。
+ 栈不空，此时 root 为 null。弹栈，root 此时为 5。即原本的根节点。其值不小于`inorderValue` 的值，更新 `inorderValue` 值为 5。root 更新为其右子树节点，即节点值为 4的节点。
+ 栈空，当 root 不为 null。进入第二层 `while` 循环，压栈，此时栈中有元素 `4，3` 的节点。弹栈，root 此时为节点值 3 的节点。**其小于 `inorderValue` 的值 5** ，返回 `false` 。

#### 复杂度分析

+ 时间复杂度：O(n)。二叉树中每个节点最多被访问 1 次。
+ 空间复杂度：O(n)。stack 中最多存储 n 个节点。（此时全部为左子树。退化成链表。）

---

### 递归算法

要解决这道题首先我们要了解二叉搜索树有什么性质可以给我们利用，由题目给出的信息我们可以知道：**如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。**

这启示我们设计一个递归函数 `helper(root, lower, upper)` 来递归判断，函数表示考虑以 `root` 为根的子树，判断子树中所有节点的值是否都在 `(l,r)` 的范围内（注意是开区间）。如果 `root` 节点的值 `val` 不在 `(l,r)` 的范围内说明不满足条件直接返回，否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。

那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 `upper` 改为 `root.val`，即调用 `helper(root.left, lower, root.val)`，因为左子树里所有节点的值均小于它的根节点的值。同理递归调用右子树时，我们需要把下界 `lower` 改为 `root.val`，即调用 `helper(root.right, root.val, upper)`。



为什么需要加入上下界呢？

![假的 BST 的情况](https://assets.ryantech.ltd/20200807145447.png)

对于上面给定的图片中的情况，如果没有带入上下界，就会遗漏掉 BST 的定义中的某些定义，造成错误。错误的代码如下：

```java
boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (root.left != null && root.val <= root.left.val) return false;
    if (root.right != null && root.val >= root.right.val) return false;

    return isValidBST(root.left)
        && isValidBST(root.right);
}
```



#### 正确的代码

```java
// 逐个子树判断上下界的方法
public boolean helper(TreeNode node, Integer lower, Integer upper) {
    if (node == null) return true;

    int val = node.val;
    // 根据 BST 的定义：
    // 节点的左子树只包含小于当前节点的数。
    // 节点的右子树只包含大于当前节点的数。
    // 如果不符合，则返回 false
    if (lower != null && val <= lower) return false;
    if (upper != null && val >= upper) return false;

    // 递归的进行判断
    if (!helper(node.left, lower, val)) return false; // 首先判断左子树
    return helper(node.right, val, upper); // 再判断右子树
}

public boolean isValidBST(TreeNode root) {
    return helper(root, null, null);
}
```



#### 复杂度分析

+ 时间复杂度：`O(n)`。二叉树中每个节点最多被访问 `1` 次。
+ 空间复杂度：`O(n)`。树退化成链表。

---

## 💡总结：

二叉树的遍历，是二叉树题目的基础。

其 2 种方式，分别为 **递归形式** 和 **非递归形式**（使用 stack 来辅助），是非常重要的基础知识。

