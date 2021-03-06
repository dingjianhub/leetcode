

# 1029_TwoCityScheduling_两地调度之优化算法

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/two-city-scheduling/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/two-city-scheduling/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Greedy](https://leetcode.com/tag/greedy/)**



---

## 📃题目描述

公司计划面试 `2N` 人。第 `i` 人飞往 A 市的费用为 `costs[i][0]`，飞往 B 市的费用为 `costs[i][1]`。

返回将每个人都飞到某座城市的最低费用，要求每个城市都有 `N` 人抵达。



**样例 1：**

```
输入：[[10,20],[30,200],[400,50],[30,20]]
输出：110
解释：
第一个人去 A 市，费用为 10。
第二个人去 A 市，费用为 30。
第三个人去 B 市，费用为 50。
第四个人去 B 市，费用为 20。

最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
```

**提示：**

1. `1 <= costs.length <= 100`
2. `costs.length` 为偶数
3. `1 <= costs[i][0], costs[i][1] <= 1000`



****

## 🏹🎯解题思路

首先让全部的人去到 A 城市，再计算去 B 城市比去 A 城市节约的路费，最后 N 个最小节约路费从 全A路费 中扣除即可。

例如 `[400,50]`，去到 B 城市可以节约 `-350`，由于前面加了 `400`，这里减去 `350`，相当于选择去 `B` 城市了。



#### 代码实现

```java
public int twoCitySchedCost(int[][] costs) {
    int minCost = 0;
    int length = costs.length;
    for (int i = 0; i < length; i++) {
        minCost += costs[i][0];
        // costs[i][1] 为选择去 B 城市，节省的费用
        costs[i][1] = costs[i][1] - costs[i][0];
    }
    Queue<Integer> queue = new PriorityQueue<>();
    int idx = 0;
    while (idx < length) {
        queue.offer(costs[idx][1]);
        idx++;
    }
    // 优先队列前 N 个为去 B 节约的花费
    for (int i = 0; i < length / 2; i++) {
        minCost += queue.remove();
    }
    return minCost;
}
```



#### 复杂度分析

+ 时间复杂度： `O(n)` 。优先队列的插入和删除的时间复杂度都是 `O(log n)` 。总体上复杂度为 `O(n)` 。
+ 空间复杂度： `O(n)` 。一个优先队列存储全部的数据。



## 💡总结

简单贪心题入门。



