package leetcode283;

public class Solution {
    /**
     * 解题思路：1.遍历nums 2.如果遇到0，
     * 向后寻找第一个非零数，与其进行位置交换。
     * */
    public void moveZeroes(int[] nums) {
        for(int i=0; i<nums.length; i++){
            if(nums[i]==0){
                for(int j=i+1; j<nums.length; j++){
                    if(nums[j]!=0){
                        nums[i] = nums[j];
                        nums[j] =0;
                        break;
                    }
                }
            }
        }
    }
}
/**
 *
 *
 * class Solution {
 *     public void moveZeroes(int[] nums) {
 *         int n = nums.length, left = 0, right = 0;
 *         while (right < n) {
 *             if (nums[right] != 0) {
 *                 swap(nums, left, right);
 *                 left++;
 *             }
 *             right++;
 *         }
 *     }
 *
 *     public void swap(int[] nums, int left, int right) {
 *         int temp = nums[left];
 *         nums[left] = nums[right];
 *         nums[right] = temp;
 *     }
 * }
 *
 **/