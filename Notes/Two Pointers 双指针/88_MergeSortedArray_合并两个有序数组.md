

# 88_MergeSortedArray_合并两个有序数组

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/merge-sorted-array/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/merge-sorted-array/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Two Pointers](https://leetcode.com/tag/two-pointers/)** 
+ **[Array](https://leetcode.com/tag/array/)**

---

## 📃题目描述：

给你两个有序整数数组 `nums1` 和 `nums2`，请你将 `nums2` 合并到 `nums1` 中*，*使 `nums1` 成为一个有序数组。

**说明：**

+ 初始化 `nums1` 和 `nums2` 的元素数量分别为 `m` 和 `n` 。
+ 你可以假设 `nums1` 有足够的空间（空间大小大于或等于 `m + n`）来保存 `nums2` 中的元素。



**样例 1：**

```
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```



再额外提供几个样例：

```java
int[] nums1 = {1, 2, 4, 5, 6, 0};
int[] nums2 = {3};
merge(nums1, 5, nums2, 1);
```

```java
int[] nums3 = {1, 2, 3, 0, 0, 0, 0, 0};
int[] nums4 = {2, 5, 6};
new Solution1().merge(nums3, 3, nums4, 3);
```



****

## 🏹🎯解题思路

设计到数组的搬迁问题，数组天然支持随机访问，是数组的一大优势，但是需要去修改数组中的值，尤其是批量移动搬迁数组中的数据，就会显现劣势。

想象一个场景：对数组进行扩容，每次扩容仅仅将数组扩容 1 个大小，如果需要扩容很多次，每次都需要重复去搬迁数据。如果按照这个思路去进行本题的分析。仅仅是搬动数据就很耗费时间。

一种常见的思路是：覆写数据。将数据搬迁后，把一部分已经写了数据的空间，看成是空的，进行数据覆写。但是由于数据已经被搬迁，整个数组的数据不会丢失。

另外一种思路是：从数组的尾部开始处理，从后先前处理数据，详见：[面试题05. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/) ，很多情况下可以将复杂度降低为 `O(n)` 。

#### 覆写数组中的元素 + 双指针

可以确定是，merge 后数组的长度为 `m + n` 。首先，将 `nums1` 中的数据向后搬迁 `n` 个位置，即为 `nums2` 中元素留出空间。

然后，使用双指针策略，依次比较数字大小，将小的元素写入 `nums1` 中，从 `nums1` 的 `0` 下标位置开始写起。

最后，进行判空操作，将还没有走到头的数组的数据，写入 nums1 中即可。

整个代码中，比较容易出错的位置在数组的边界条件。也是主要考察点。



#### 代码实现

```java
/**
     * @param nums1 数组 1
     * @param m     nums1 中的元素个数
     * @param nums2 数组 2
     * @param n     nums2 中的元素个数
     */
public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (nums2.length == 0 || n <= 0 || nums1.length == m) {
        return;
    }

    // 第一个卡点：数据搬迁
    // 首先将 nums1 中的元素移动向后移动
    // 使得 nums1 前面的 m 个数字成为“脏数据”，可以被直接覆写
    for (int i = n + m - 1, j = m - 1; j >= 0; j--) {
        nums1[i] = nums1[j];
        i--;
    }

    int nums2Index = 0;
    int nums1Index = n;
    int index = 0;
    while (nums2Index < n && nums1Index < m + n) {
        // nums1 中元素较小
        if (nums1[nums1Index] <= nums2[nums2Index]) {
            nums1[index] = nums1[nums1Index];
            index++;
            nums1Index++;
        } else {
            nums1[index] = nums2[nums2Index];
            index++;
            nums2Index++;
        }
    }
    // nums2 中还有剩余元素
    if (nums2Index < n) {
        for (int i = nums2Index; i < n; i++) {
            nums1[index] = nums2[i];
            index++;
        }
    } else {
        for (int i = nums1Index; i < m + n; i++) {
            nums1[index] = nums1[i];
            index++;
        }
    }
}
```



#### 复杂度分析

+ 时间复杂度：`O(n)`。每个元素都要被遍历一次。
+ 空间复杂度：`O(1)`。除了引入几个指针，不需要额外的空间。



## 💡总结：

对边界条件的考察。

需要对边界条件的分析和把握很准确，否则难以写出无 bug 的代码。

### 相似题目

#### [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

> 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
>
> 示例：
>
> ```
> 输入：1->2->4, 1->3->4
> 输出：1->1->2->3->4->4
> ```
>
> 



#### [986. 区间列表的交集](https://leetcode-cn.com/problems/interval-list-intersections/)

