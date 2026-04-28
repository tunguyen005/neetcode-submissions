class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int ans = nums[0];

        if (nums.length == 1) {
            return nums[0];
        }

        while (l <= r) {
            if (nums[l] < nums[r]) {
                ans = Math.min(ans, nums[l]);
            }
            int m = (l + r) / 2;

            ans = Math.min(ans, nums[m]);
            if (nums[l] <= nums[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;

    }
}
