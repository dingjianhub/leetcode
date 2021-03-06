

# 237_DeleteNodeInALinkedList_删除链表中的节点

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/delete-node-in-a-linked-list/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Linked List](https://leetcode.com/tag/linked-list/)**

---

## 📃题目描述

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- `head = [4,5,1,9]`，它可以表示为：

![img](https://assets.ryantech.ltd/237_example.png)

**样例 1：**

```
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```



**样例 2：**

```
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```



**说明：**

- 链表至少包含两个节点。
- 链表中所有节点的值都是唯一的。
- 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
- 不要从你的函数中返回任何结果。



****

## 🏹🎯解题思路

这道题属于：题目看起来有点绕，但是方法很简单的题目。

有点类似于 `数组的覆写`，这里给定的 `node` 是需要删除的 `node`，可以用 `node.next.val` 覆写 `node.val` ，再做一次指针的变换即可。



#### 代码实现

```java
// 覆写策略
public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```



#### 复杂度分析

+ 时间复杂度： `O(1)` 。   
+ 空间复杂度：`O(1)`  。



## 💡总结

扯得有点远了。

链表考察的一个重点就是指针的变换。

数组的一个考察重点就是 idx 的理解和操作，和指针操作有些许相似。

