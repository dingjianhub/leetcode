package ltd.ryantech.stack.removeAllAdjacentDuplicatesInString1047;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.stack.removeAllAdjacentDuplicatesInString1047
 * @description 1047. 删除字符串中的所有相邻重复项
 * @leetcode_CN_url // https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 * @hard_level Easy
 * @tag Stack // https://leetcode-cn.com/tag/stack/
 * @create 2020/10/14 23:12
 **/

public class Solution1 {
    // 使用 栈 辅助操作
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();

        int i = 0;
        int len = S.length();
        StringBuilder sb = new StringBuilder();

        for (i = 0; i < len; i++) {
            if (stack.isEmpty() || stack.peekLast() != S.charAt(i)) {
                stack.offerLast(S.charAt(i));
            } else {
                stack.pollLast();
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        // 转置，获得正序
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = new String("abbaca");
        System.out.println(new Solution1().removeDuplicates(s));
    }
}
