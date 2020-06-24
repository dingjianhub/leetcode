

# 191_NumberOf1Bits_位1的个数

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/number-of-1-bits/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/number-of-1-bits/)

📗Difficulty： **Easy**

🎯Tags：

+ **[Bit Manipulation](https://leetcode-cn.com/tag/bit-manipulation/)** 



---

## 📃题目描述

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 **‘1’** 的个数（也被称为[汉明重量](https://baike.baidu.com/item/汉明重量)）。



**样例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```



**样例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```



**样例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```



**提示：**

+ 请注意，在某些语言（如 `Java`）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
+ 在 `Java` 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 `-3`。



****

## 🏹🎯解题思路

> 以下思路来自 leetcode-cn [官方题解](https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode/)，感谢工作人员的精心编写。

### 位移动

这种方法比较直观易懂，思路也很简单。

给定的是 `int` 类型，在 Java 中长度为 32 位，那么检查这 32 个位置即可。使用一个掩码 `mask = 1` 来和原数字做 `&` 运算，然后左移动 `mask` ，检查原数字从右向左的第二位。

#### 代码实现



```java
// you need to treat n as an unsigned value
public int hammingWeight(int n) {
    int res = 0;
    int mask = 0x1;
    for (int idx = 0; idx < 32; idx++) {
        if ((n & mask) != 0) {
            res++;
        }
        mask = mask << 1;
    }
    return res;
}
```



#### 复杂度分析

+ 时间复杂度：`O(1)` 。在本题中数字的长度是确定的为 32 位长度。
+ 空间复杂度：`O(1)` 。



### 反转最后一个位置的数字



我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 `1` 反转，并把答案加一。当数字变成 `0` 的时候偶，我们就知道它没有 `1` 的位了，此时返回答案。

这里关键的想法是对于任意数字 `n` ，将 `n` 和 `n - 1` 做与运算，会把最后一个 `1` 的位变成 `0` 。为什么？考虑 `n` 和 `n - 1` 的二进制表示。



![图示](https://assets.ryantech.ltd/20200624164301.jpg)



#### 代码实现

```java
public class Solution1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 23;
        System.out.println(new Solution1().hammingWeight(n));
    }
}
```



#### 复杂度分析

+ 时间复杂度：`O(1)` 。在本题中数字的长度是确定的为 32 位长度。
+ 空间复杂度：`O(1)` 。



## 💡总结

### 相似题目

[231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)

> 这道题目可以利用 191 的思路 2 来求解。

