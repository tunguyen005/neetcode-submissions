class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 0, r = 0;
        for (int pile : piles) {
            r = Math.max(r, pile);
        }

        while (l < r) {
            int m = l + (r - l) / 2;
            if (canFinish(piles, m, h)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l; 
    }

    private static boolean canFinish(int[] piles, int speed, int h) {
        int hours = 0;
        for (int pile : piles) {
            hours += Math.ceil((double) pile / speed);
        }
        return hours <= h;
    }
}
