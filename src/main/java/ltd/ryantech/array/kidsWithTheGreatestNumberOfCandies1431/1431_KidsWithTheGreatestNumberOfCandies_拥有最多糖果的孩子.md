

# 1431_KidsWithTheGreatestNumberOfCandies_拥有最多糖果的孩子

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Array](https://leetcode.com/tag/array/)**

---

## 📃题目描述

给你一个数组 `candies` 和一个整数 `extraCandies` ，其中 `candies[i]` 代表第 `i` 个孩子拥有的糖果数目。

对每一个孩子，检查是否存在一种方案，将额外的 `extraCandies` 个糖果分配给孩子们之后，此孩子有 **最多** 的糖果。注意，允许有多个孩子同时拥有 **最多** 的糖果数目。



**样例 1：**

```
输入：candies = [2,3,5,1,3], extraCandies = 3
输出：[true,true,true,false,true] 
解释：
孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
```



**样例 2：**

```
输入：candies = [4,2,1,1,2], extraCandies = 1
输出：[true,false,false,false,false] 
解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
```



**提示：**

- `2 <= candies.length <= 100`
- `1 <= candies[i] <= 100`
- `1 <= extraCandies <= 50`



****

## 🏹🎯解题思路

2020 年 6 月 1 日，国际儿童节。

leetcode-cn 6 月份每日一题的第一题。



如果希望一个小朋友拥有最多的糖果，那么最佳的选择是把全部的 `extraCandies` 都给这个小朋友一个人。如果这么操作后，还是比持有原始糖果数最多的小朋友少，那么返回 `false` 。

一次遍历，找出 `candies` 数组中的最大值，比较 `candies[i] + extraCandies >= maxCandies` 即可。



#### 代码实现

```java
public class Solution1 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Integer.MIN_VALUE;
        for (int num : candies) {
            maxCandies = Math.max(num, maxCandies);
        }
        List<Boolean> res = new LinkedList<>();
        for (int num : candies) {
            if (num + extraCandies >= maxCandies) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        List<Boolean> res = new Solution1().kidsWithCandies(candies, extraCandies);
        for (Boolean i : res) {
            System.out.println(i);
        }
    }
}
```



#### 复杂度分析

+ 时间复杂度： `O(n)`  。遍历 2 次数组。
+ 空间复杂度：`O(1)` 。不计算最后返回结果需要的额外空间。



## 💡总结

2020 年 6 月 1 日，星期一。

今日 A 股市场全面上涨，上证指数上涨 2.21 %，收于 2915.43 点；深证成指上涨 3.31 %，收于 11102.15 点。

儿童节快乐~



