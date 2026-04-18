class Solution {
    public boolean hasDuplicate(int[] nums) {
        HashSet<Integer> seenNums = new HashSet<>();

        for (int num : nums) {
            if (seenNums.contains(num)) {
                return true;
            }
            seenNums.add(num);
        }
        return false;
    }
}