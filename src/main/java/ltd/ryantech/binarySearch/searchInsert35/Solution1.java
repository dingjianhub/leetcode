package ltd.ryantech.binarySearch.searchInsert35;

/**
 * @author jerry
 * @program leetcode
 * @package_name ltd.ryantech.binarySearch.searchInsert35
 * @description 35. 搜索插入位置
 * @leetcode_CN_url // https://leetcode-cn.com/problems/search-insert-position/
 * @leetcode_US_url // https://leetcode.com/problems/search-insert-position/
 * @hard_level Easy
 * @tag Binary Search // https://leetcode-cn.com/tag/binary-search/
 * @create 2020/06/21 15:10
 **/

public class Solution1 {
    // 经典 “三分式” 两分搜索
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 此时完成了全部元素的遍历，没有找到元素，需要插入元素
        // 有 left > right,程序希望继续惯性查找下去，但是已经不满足循环，全部查找一次了
        // 那么此时的 left 就应该是 target 本来应该在的位置了
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(new Solution1().searchInsert(nums, 5));

        int[] nums1 = {1, 3, 5, 6};
        System.out.println(new Solution1().searchInsert(nums1, 2));
    }
}
