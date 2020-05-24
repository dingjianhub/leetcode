

# 986_IntervalListIntersections_区间列表的交集

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/interval-list-intersections/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/interval-list-intersections/)

📗Difficulty：	**Medium**	

🎯Tags：

+ **[Two Pointers](https://leetcode.com/tag/two-pointers/)** 
+ **[Array](https://leetcode.com/tag/array/)**

---

## 📃题目描述：

给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。

返回这两个区间列表的交集。

（形式上，闭区间 `[a, b]`（其中 `a <= b`）表示实数 `x` 的集合，而 `a <= x <= b`。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，`[1, 3]` 和 `[2, 4]` 的交集为 `[2, 3]`。）

![样例 1](https://assets.ryantech.ltd/interval1.png)

**样例 1：**

```
输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
注意：输入和所需的输出都是区间对象组成的列表，而不是数组或列表。
```

**提示：**

+ `0 <= A.length < 1000`
+ `0 <= B.length < 1000`
+ `0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9`



补充几个额外样例，以 `Java` 的数组形式给出：

```java
int[][] A = {{3, 10}};
int[][] B = {{5, 10}};
```



****

## 🏹🎯解题思路

leetcode-cn 的评论区有用户给出了一个很形象的说明：**拉拉链**。

下面结合演示图例进行说明：

![演示图例](https://assets.ryantech.ltd/2020-05-24 13.38.48.156.jpg)

A，B 区间相对的位置情况如图示给出的四个情形。我们假定 B 是不动的，移动 A ，来寻找其中的关系。（相对的，固定 A 来看 B 的位置也是可以的。这样可以简化思考，避免多思考或者少思考而漏解。）

设置 2 个指针 `i，j`，指向 A，B 区间。

+ 如果是 A，B 不相交的情况，那么需要移动对应的指针，如果 A 在前，就向后移动 A 的指针；如果 B 在前，就向后移动 B 的指针。

+ 如果相交了。那么 2 个区间的交集就是：`start` 为A，B 中的较大者，`end` 为 A，B 中较小者。然后移动指针，这里移动指针又有 2 种情况。
    + A 结束地比 B 早，那么 B 还有剩余，需要再回到参与交集计算。即：`A[i][1] <= B[j][1]` ，那么就向后移动 A 的指针。
    + 否则，就向后移动 B 的指针。

**需要注意到的一个点是，需要始终保持索引的合法性，避免索引的越界。**

**在代码实现中的体现是，if 判断每次只会命中一个情况，if 判断外围使用 while 循环，保证索引的合法性。**



#### 代码实现

```java
public int[][] intervalIntersection(int[][] A, int[][] B) {
    int i = 0;
    int j = 0;
    int begin = 0;   // 交集开始位置
    int end = 0;     // 交集结束位置
    List<int[]> res = new LinkedList<>();
    while (i < A.length && j < B.length) {
        // 内部 if 判断是核心
        if (A[i][1] < B[j][0]) {
            i++;
        } else if (A[i][0] > B[j][1]) {
            j++;
        } else {
            begin = Math.max(A[i][0], B[j][0]);
            end = Math.min(A[i][1], B[j][1]);
            res.add(new int[]{begin, end});
            // A 区间结束比 B 早，A 向后移动一个位置
            if (A[i][1] <= B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
    }
    int resSize = res.size();
    int[][] resArray = new int[resSize][2];
    for (int index = 0; index < resSize; index++) {
        resArray[index] = res.remove(0);    // 每次移除链表头部即可
    }
    return resArray;
}
```



#### 复杂度分析

+ 时间复杂度：`O(n)`。每个元素都需要遍历到一次。
+ 空间复杂度：`O(1)`。不计算额外的存储结果的空间。



## 💡总结：

合理“数形结合”，面对较为复杂的分析情况时，要合理进行分析。

### 相似问题

#### [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

> 给你两个有序整数数组 *nums1* 和 *nums2*，请你将 *nums2* 合并到 *nums1* 中*，*使 *nums1* 成为一个有序数组。
>
> 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
> 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
>
> ```
> 输入:
> nums1 = [1,2,3,0,0,0], m = 3
> nums2 = [2,5,6],       n = 3
> 
> 输出: [1,2,2,3,5,6]
> ```
>
> 