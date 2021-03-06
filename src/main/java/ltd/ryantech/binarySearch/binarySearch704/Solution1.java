package ltd.ryantech.binarySearch.binarySearch704;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.binarySearch.binarySearch704
 * @description 704. 二分查找
 * @leetcode_CN_url // https://leetcode-cn.com/problems/binary-search/
 * @leetcode_US_url // https://leetcode.com/problems/binary-search/
 * @hard_level Easy
 * @tag Binary Search // https://leetcode-cn.com/tag/binary-search/
 * @create 2020/06/21 14:53
 **/

public class Solution1 {
    // “三分式” 两分搜索
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // 下取整
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // mid 和 mid 左边的元素都小于 target
                // 下一轮搜索的范围在 [mid + 1,right]
                left = mid + 1;
            } else {
                // mid 和 mid 右边的元素都超出 target
                // 下一轮搜索的范围在 [left,mid - 1]
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(new Solution1().search(nums, 9));

        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        System.out.println(new Solution1().search(nums1, 2));
    }
}
