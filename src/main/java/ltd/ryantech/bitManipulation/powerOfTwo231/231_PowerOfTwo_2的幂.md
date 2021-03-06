

# 231_PowerOfTwo_2的幂

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/power-of-two/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/power-of-two/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Math](https://leetcode-cn.com/tag/math/)**
+ **[Bit Manipulation](https://leetcode-cn.com/tag/bit-manipulation/)** 



---

## 📃题目描述

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

**样例 1：**

```
输入: 1
输出: true
解释: 2^0 = 1
```



**样例 2：**

```
输入: 16
输出: true
解释: 2^4 = 16
```



**样例 3：**

```
输入: 218
输出: false
```



****

## 🏹🎯解题思路

不考虑数字为 0 和负数的情况，所有为 2 的幂次的数字的二进制数字都有一个规律： 除最高位为 1 之外，其他位置上都是 0。其减去 1 后，除最高位为 0 以外，其他位置都为 1。

那么对 n & n - 1 操作后，如果返回值为 0 则为 2 的幂次，否则不是。

#### 代码实现

```java
public class Solution1 {
    public boolean isPowerOfTwo(int n) {
        return n != 0 && (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        int num1 = 1;
        System.out.println(new Solution1().isPowerOfTwo(num1));

        int num2 = 0;
        System.out.println(new Solution1().isPowerOfTwo(num2));

        int num3 = -4;
        System.out.println(new Solution1().isPowerOfTwo(num3));

        int num4 = 8;
        System.out.println(new Solution1().isPowerOfTwo(num4));
    }
}
```



#### 复杂度分析

+ 时间复杂度：`O(1)` 。
+ 空间复杂度：`O(1)` 。 



### Integer.highestOneBit(n) 方法

`Integer.highestOneBit(n)` 会返回其最高的位，比如 `1011` 会返回 `1000` 。

#### 代码分析

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
       if (n <= 0) {
        return false;
        }
        return Integer.highestOneBit(n) == n;
    }
}
```



#### 复杂度分析

+ 时间复杂度：`O(1)` 。
+ 空间复杂度：`O(1)` 。 



## 💡总结

### 相似题目

[191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)



