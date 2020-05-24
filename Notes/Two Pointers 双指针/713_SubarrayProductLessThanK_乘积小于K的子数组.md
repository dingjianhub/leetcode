

# 713_SubarrayProductLessThanK_乘积小于K的子数组

## 📌题目详情

[leetcode 题目地址](https://leetcode-cn.com/problems/subarray-product-less-than-k/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/subarray-product-less-than-k/)

📗Difficulty：	**Medium**	

🎯Tags：

+ **[Two Pointers](https://leetcode.com/tag/two-pointers/)** 
+ **[Array](https://leetcode.com/tag/array/)**

---

## 📃题目描述：

给定一个正整数数组 `nums`。

找出该数组内乘积小于 `k` 的连续的子数组的个数。

**样例 1：**

```
输入: nums = [10,5,2,6], k = 100
输出: 8
解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
```

**说明：**

+ `0 < nums.length <= 50000`
+ `0 < nums[i] < 1000`
+ `0 <= k < 10^6`



****

## 🏹🎯解题思路

### 双指针法

对于每一个 `right` 指针，需要找到最左边的 `left` 指针，使得 `left - right` 的区间的子数组的积满足小于 `k`。

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    // 非常特殊的边界条件 特判
    if (k <= 1) {
        return 0;
    }
    int left = 0;
    int res = 0;
    int mul = 1;
    for (int right = 0; right < nums.length; right++) {
        mul *= nums[right];
        // 找到满足的最左 left
        while (mul >= k) {
            mul = mul / nums[left];
            left++;
        }
        // 避免重复计算
        res += right - left + 1;
    }
    return res;
}
```



#### 复杂度分析

+ 时间复杂度：`O(n)`。`for` 循环的复杂度为 `O(n)` ，而 `left` 最多移动 `n` 次，因此总的复杂度为 `O(n)` 
+ 空间复杂度：`O(1)`。

## 💡总结：

相似题目：

#### [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

> 给你一个整数数组 `nums` ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
>
> 样例 1：
>
> 输入: [2,3,-2,4]
> 输出: 6
> 解释: 子数组 [2,3] 有最大乘积 6。



#### [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

> 给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 

