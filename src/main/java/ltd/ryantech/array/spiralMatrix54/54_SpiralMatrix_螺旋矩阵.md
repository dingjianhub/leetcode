

# 54_SpiralMatrix_螺旋矩阵

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/spiral-matrix/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/spiral-matrix/)

📗Difficulty：**Medium**	

🎯Tags：

+ **[Array](https://leetcode-cn.com/tag/array/)**



---

## 📃题目描述

给定一个包含 `m x n` 个元素的矩阵（`m` 行, `n` 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

**样例 1：**

```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
```



**样例 2：**

```
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```



****

## 🏹🎯解题思路


类似的题目还有，《蛇形填数》。类似于贪吃蛇，只能有 四个方向去移动，而且不能超越一定的范围。

这类题目，称其为**“模拟题”**，就是说，本题的解题过程是模拟人工解决这类问题的过程，并不涉及到很复杂的数据结构和算法。



首先，观察填数的顺序。从左上角开始，然后向右到边界，再向下，再向上，再向右边。**变换 4 次方向。**

![“蛇头”变换过程](https://assets.ryantech.ltd/20200605135753.jpg)

其中一个疑问在，如何在变换方向后确定“折返点”？

对于外圈的点位来说，可以判断是否越界。但是对于内圈的点位，必须要有个指示的东西，否则会重复走走过的路，造成 bug 。

而对于越界的判断有 2 种思路：

+ 先污染，后治理。
+ 不污染，向后看一个。

下面给出这  2 种思路的代码。

### 先污染，后治理


设置一个和输入矩阵等大的矩阵，其中每个元素的初始值为 0，代表原始矩阵相应位置没有被访问过。

每次访问过该位置后，将该位置的值写为 1，代表该位置的元素被访问过。如果遇到该位置元素被访问了，那么就“撞南墙”，改变方向。直到每个元素被访问到。




#### 代码实现

```java
public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
        return new LinkedList<>();
    }

    int row = matrix.length;
    int column = matrix[0].length;
    int resSize = row * column;
    int[][] visited = new int[row][column];
    List<Integer> ans = new LinkedList<>();
    int x = 0;
    int y = 0;
    int tot = 0;
    while (tot < resSize) {
        while (y < column && visited[x][y] == 0) {
            visited[x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
            y++;
        }
        y--; // 修正
        x++; // 换到下一行
        while (x < row && visited[x][y] == 0) {
            visited[x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
            x++;
        }
        x--; // 修正
        y--; // 换行
        while (y >= 0 && visited[x][y] == 0) {
            visited[x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
            y--;
        }
        y++; // 修正
        x--; // 换行
        while (x >= 0 && visited[x][y] == 0) {
            visited[x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
            x--;
        }
        x++; // 修正
        y++; // 换行
    }
    return ans;
}
```



#### 复杂度分析

+ 时间复杂度： `O(n)` 。每个元素都被访问一次。
+ 空间复杂度： `O(n)` 。需要设置一个和输入数组等大的数组来标记元素是否被访问过。



### 向后看一步，精准卡位

先检查此位置是否越界，代码更加简洁清晰。



#### 代码实现

```java
public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
        return new LinkedList<>();
    }

    int row = matrix.length;
    int column = matrix[0].length;
    int resSize = row * column;
    int[][] visited = new int[row][column];
    List<Integer> ans = new LinkedList<>();
    int x = 0;
    int y = 0;
    int tot = 1;
    ans.add(matrix[x][y]);
    visited[x][y] = 1;
    while (tot < resSize) {
        while (y + 1 < column && visited[x][y + 1] == 0) {
            visited[x][++y] = 1;
            ans.add(matrix[x][y]);
            tot++;
        }
        while (x + 1 < row && visited[x + 1][y] == 0) {
            visited[++x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
        }
        while (y - 1 >= 0 && visited[x][y - 1] == 0) {
            visited[x][--y] = 1;
            ans.add(matrix[x][y]);
            tot++;
        }
        while (x - 1 >= 0 && visited[x - 1][y] == 0) {
            visited[--x][y] = 1;
            ans.add(matrix[x][y]);
            tot++;
        }
    }
    return ans;
}
```



#### 复杂度分析

+ 时间复杂度： `O(n)` 。每个元素都被访问一次。
+ 空间复杂度： `O(n)` 。需要设置一个和输入数组等大的数组来标记元素是否被访问过。



## 💡总结

### 相似题目

[59. 螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/)

[面试题29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)



