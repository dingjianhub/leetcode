package ltd.ryantech.graph.findTheTownJudge997;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.graph.findTheTownJudge997
 * @description 997. 找到小镇的法官
 * @leetcode_CN_url // https://leetcode-cn.com/problems/find-the-town-judge/
 * @leetcode_US_url // https://leetcode.com/problems/find-the-town-judge/
 * @hard_level Easy
 * @tag Graph // https://leetcode-cn.com/tag/graph/
 * @create 2020/05/10 18:41
 **/

public class Solution1 {
    public int findJudge(int N, int[][] trust) {
        int[][] counter = new int[N + 1][2];
        for (int[] ints : trust) {
            counter[ints[0]][0]++; // 出度
            counter[ints[1]][1]++; // 入度
        }
        for (int i = 1; i <= N; i++) {
            // 法官的 出度 - 入度 等于 N - 1
            if (counter[i][1] - counter[i][0] == N - 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] trust = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(new Solution1().findJudge(N, trust));
    }
}
