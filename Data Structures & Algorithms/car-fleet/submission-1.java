class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> position[b] - position[a]);

        int count = 0;
        double prev = 0;

        for (int i : idx) {
            double time = (double) (target - position[i]) / speed[i];
            if (time > prev) {
                count++;
                prev = time;
            }
        }

        return count;
    }
}
