# 476_NumberComplement_数字的补数

## 📌题目详情

[leetcode 题目地址](https://leetcode.com/problems/number-complement/)

[leetcode-cn 题目地址](https://leetcode-cn.com/problems/number-complement/)

📗Difficulty：**Easy**	

🎯Tags：

+ **[Bit Manipulation](https://leetcode.com/tag/bit-manipulation/)** 

---

## 📃题目描述：

给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。 

**样例 1：**

```
输入: 5
输出: 2
解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
```

**样例 2：**

```
输入: 1
输出: 0
解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
```

**注意：**

+  给定的整数保证在 32 位带符号整数的范围内。 
+  你可以假定二进制数不包含前导零位。 

****

## 🏹🎯解题思路

### 求和法

```java
// 给定 100110
// 返回 011001
// 相加 111111
// 那么求出和，再减去即可
public int findComplement(int num) {
    int i = 0;
    int j = 0;
    while (i < num) {
        i = i + (int) Math.pow(2, j);
        j++;
    }
    return i - num;
}
```

#### 复杂度分析

+ 时间复杂度：`O(log n)`
+ 空间复杂度：`O(1)`

### 位运算

```java
/**
     * num          = 00000101
     * mask         = 00000011
     * ~num         = 11111010
     * mask & ~num  = 00000010
     */
public int findComplement(int num) {
    return ~num & (Integer.highestOneBit(num) - 1);
}
```

#### 复杂度分析

+ 时间复杂度：`O(1)`
+ 空间复杂度：`O(1)`



## 💡总结：

优先使用位运算，这是更加优雅合适的解法。

[1009. 十进制整数的反码](https://leetcode-cn.com/problems/complement-of-base-10-integer/) 是一道和 [476](https://leetcode-cn.com/problems/number-complement/) 一样的题目，只是增加了输入为非负整数，那么 0 也是符合的。

如果是照抄 [476](https://leetcode-cn.com/problems/number-complement/) 的代码，将无法通过测试。需要简单修改为：

```java
public int bitwiseComplement(int N) {
	return N == 0 ? 1 : ~N & (Integer.highestOneBit(N) - 1);
}
```

