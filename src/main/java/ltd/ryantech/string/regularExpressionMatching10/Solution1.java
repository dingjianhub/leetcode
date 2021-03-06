package ltd.ryantech.string.regularExpressionMatching10;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.string.regularExpressionMatching10
 * @description 正则表达式匹配
 * @leetcode_CN_url // https://leetcode-cn.com/problems/regular-expression-matching/
 * @leetcode_US_url // https://leetcode.com/problems/regular-expression-matching/
 * @hard_level Hard
 * @tag String // https://leetcode-cn.com/tag/string/
 * @create 2020/05/29 15:46
 **/

// 来自 《leetcode && 拉钩网  300 分钟搞定算法面试》
// 不是特别好的递归算法，其中细节考虑很多
public class Solution1 {
    // 从前往后进行分析，进行递归操作
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return isMatch(s, 0, p, 0);
    }

    public boolean isMatch(String s, int i, String p, int j) {
        int m = s.length();
        int n = p.length();

        // 递归结束的时间：j 遍历完 p 字符串，即可跳出递归
        // 如果 i 也刚好完成遍历，那么 s 和 p 完全匹配
        if (j == n) {
            return i == m;
        }

        // j 的后一个字符不是 '*' , 直接递归调用 isMatch 方法即可
        if (j == n - 1 || p.charAt(j + 1) != '*') {
            // i < m , 在 p 比 s 长的情况，此时也不匹配
            return (i < m) && firstMatch(s, i, p, j) && isMatch(s, i + 1, p, j + 1);
        }

        // j 的后一个字符是 '*'
        // 一种特殊的情况是，'*' 是最后一个字符
        if (j < n - 1 && p.charAt(j + 1) == '*') {
            // while 循环是本题核心
            while ((i < m) && firstMatch(s, i, p, j)) {
                if (isMatch(s, i, p, j + 2)) {
                    return true;
                }
                // 如果无法匹配
                // 尝试用星号组合去匹配更长的一段字符串
                i++;
            }
        }

        // 当 i 和 j 指向字符不相同
        // 或 i 已经遍历完 s 字符串，同时 j 指向的字符后面跟着一个 '*' 的情况
        // 使用 '*' 号组合表示为一个空字符串，然后递归下去
        if (((i < m) && !firstMatch(s, i, p, j)) || (i == m && p.charAt(j + 1) == '*')) {
            return isMatch(s, i, p, j + 2);
        }

        // 初始调用输入为 i,j 为超过 实际长度的 情况，返回 false
        return false;
    }

    public boolean firstMatch(String s, int i, String p, int j) {
        // 判断 i,j 指针所指字符是否相等
        // 如果 j 位置字符为 '.' , 那么也认为是相等的
        return p.charAt(j) == '.' || s.charAt(i) == p.charAt(j);
    }

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        System.out.println(new Solution1().isMatch(s1, p1));

        String s2 = "aa";
        String p2 = "a*";
        System.out.println(new Solution1().isMatch(s2, p2));

        String s3 = "ab";
        String p3 = ".*";
        System.out.println(new Solution1().isMatch(s3, p3));

        String s4 = "aab";
        String p4 = "c*a*b";
        System.out.println(new Solution1().isMatch(s4, p4));

        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        System.out.println(new Solution1().isMatch(s5, p5));

        String s6 = "ssippi";
        String p6 = "s*p*.";
        System.out.println(new Solution1().isMatch(s6, p6));

    }
}
