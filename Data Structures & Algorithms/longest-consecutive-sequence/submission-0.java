class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }

        int ls = 1;
        for (int num : numSet) {
            if (numSet.contains(num - 1)) {
                continue;
            }
            else {
                int curNum = num;
                int curSub = 1;
                while (numSet.contains(curNum + 1)) {
                    curNum++;
                    curSub++;
                }
                ls = Math.max(ls, curSub);
            }
        }
        return ls;
    }
}
