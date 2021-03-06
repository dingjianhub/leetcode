

# 628_MaximumProductOfThreeNumbers_三个数的最大乘积

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/maximum-product-of-three-numbers/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/maximum-product-of-three-numbers/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Math](https://leetcode.com/tag/math/)**
+ **[Array](https://leetcode.com/tag/array/)**

---

## 📃题目描述：

给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。 

**样例 1：**

```
输入: [1,2,3]
输出: 6
```



**样例 2：**

```
输入: [1,2,3,4]
输出: 24
```

**注意：**

1. 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
2. 输入的数组中任意三个数的乘积不会超出 32 位有符号整数的范围。

****

## 🏹🎯解题思路

### 线性复杂度的思路

和 [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) 类似，给定的数组中存在负数的情况。需要分情况讨论。

+ 当 3 个数字**不包含负数**时，要取得最大值，需要取最大的三个正数。
+ 当 3 个数字中**包含负数**时，要取得最大值，需要取 2 个最小的负数，负负得正，再取一个最大的正数。

### 排序取数

直接排序，取最大的 3 个正数，取最小的 2 个负数和 1 个最大的负数，然后分别相乘取最大值。

```java
public int maximumProduct(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
}
```

#### 复杂度分析

+ 时间复杂度：`O（n log n）` 排序复杂度。
+ 空间复杂度：`O（1）` 。

### 使用优先队列

```java
// 优先队列
// 空间复杂度不佳
public int maximumProduct(int[] nums) {
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    });
    PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
        maxQueue.offer(nums[i]);
        minQueue.offer(nums[i]);
    }
    int max1 = maxQueue.poll();
    int max2 = maxQueue.poll();
    int max3 = maxQueue.poll();
    int min1 = minQueue.poll();
    int min2 = minQueue.poll();
    return Math.max(max1 * max2 * max3, max1 * min1 * min2);
}
```

#### 复杂度分析

+ 时间复杂度：`O(n log n)` 使用优先队列的复杂度。
+ 空间复杂度：`O(n)` 需要额外 2 个优先队列来存储数据。

### 遍历取数

```java
public int maximumProduct(int[] nums) {
    // 找出最大的 3 个正数和最小的 2 个负数 即可
    int max1 = Integer.MIN_VALUE;
    int max2 = Integer.MIN_VALUE;
    int max3 = Integer.MIN_VALUE;
    int min1 = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
        // min1 是最小的数
        if (nums[i] <= min1) {
            min2 = min1;
            min1 = nums[i];
        } else if (nums[i] <= min2) {
            min2 = nums[i];
        }
        // max1 是最大的数字
        if (nums[i] >= max1) {
            max3 = max2;
            max2 = max1;
            max1 = nums[i];
        } else if (nums[i] >= max2) {
            max3 = max2;
            max2 = nums[i];
        } else if (nums[i] >= max3) {
            max3 = nums[i];
        }
    }
    return Math.max(max1 * max2 * max3, max1 * min1 * min2);
}
```

代码如上，比较思路较为简单易懂。

#### 复杂度分析

+ 时间复杂度：`O(n)` 。
+ 空间复杂度：`O(1)` 。

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

#### [713. 乘积小于K的子数组](https://leetcode-cn.com/problems/subarray-product-less-than-k/)

> 给定一个正整数数组 `nums`。
>
> 找出该数组内乘积小于 `k` 的连续的子数组的个数。

#### [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

> 给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 

