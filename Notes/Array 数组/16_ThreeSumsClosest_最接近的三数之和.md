

# 16_ThreeSumsClosest_最接近的三数之和

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/3sum-closest/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/3sum-closest/)

📗Difficulty： **Medium**

🎯Tags：

+ **[排序](https://leetcode-cn.com/tag/sort/)**
+ **[数组](https://leetcode-cn.com/tag/array/)**

---

## 📃题目描述

给定一个包括 `n` 个整数的数组 `nums` 和 一个目标值 `target`。找出 `nums` 中的三个整数，使得它们的和与 `target` 最接近。返回这三个数的和。假定每组输入只存在唯一答案。



**样例 1：**

```
输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
```



**提示：**

- `3 <= nums.length <= 10^3`
- `-10^3 <= nums[i] <= 10^3`
- `-10^4 <= target <= 10^4`

****

## 🏹🎯解题思路

> 以下思路来自于 [leetcode-cn 用户 灵魂画手 的题解](https://leetcode-cn.com/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/)，感谢他的精彩分析。

接着 [15. 三数之和](https://leetcode-cn.com/problems/3sum/) 来分析，请先完成后再来查看 [16. 最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/) 。

首先对数组进行排序，排序后，设置 3 个指针，一个指针从尾部开始，向后遍历，作为外循环。外循环有 `nums.lenght - 2`  次循环。另外 2 个指针，指向剩下的头尾，进行“查找”的操作。

需要注意的是：因为经过 15 题的思路，这里会惯性地进行排重操作。还是需要对情况进行细致的分析，才能从根上避免错误的发生。

![图解思路-1](https://assets.ryantech.ltd/20200624152156.png)

如上图所示，假设 `target = 0` ，此时的 `sum` 和它的差距为 3，根据有序数组的特性，需要将 `i` 向前移动，增大 `sum` 的值，以期减小差距。对于差距，使用 `Math.abs(sum - taget)` 进行求解，每次更新最小的绝对值，存入 `ans` 中。

如果 `Math.abs(sum - taget)  <  Math.abs(ans - taget)` ，那么需要减小 `j`，以此减小 `sum` 的和。

在此过程中，无所谓是否进行重复的判定，因为最后结果只关系最接近的值。如果出现重复值，对最终结果没有影响。

如果在过程中出现了严格等于 `target` 的情况，可以直接返回。



#### 代码实现

```java
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int ans = nums[0] + nums[1] + nums[2];
    for (int k = 0; k < nums.length - 2; k++) {
        if (k > 0 && nums[k] == nums[k - 1]) {
            continue;
        }
        int left = k + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[k] + nums[left] + nums[right];
            if (Math.abs(sum - target) < Math.abs(ans - target)) {
                ans = sum; // 更新最小距离
            }
            if (sum > target) {
                right--; // 需要更小的 sum
            } else if (sum < target) {
                left++; // 需要更大的 sum
            } else {
                return ans; // 严格相等，返回
            }
        }
    }
    return ans;
}
```



#### 复杂度分析

+ 时间复杂度：`O(n ^ 2)` 。排序消耗 `O(logn)`，查找消耗 `O(n ^ 2)`。
+ 空间复杂度：`O(1)` 。



---

## 💡总结

本题很容易和 15 题串题，陷入思维惯性中去，需要特别注意这种情况在面试中的发生。

并且，由于循环很多，debug 过程也比较冗余。建议在思考时，将情况考察清楚，再进行代码编写。



### 相似题目

[15. 三数之和](https://leetcode-cn.com/problems/3sum/)

