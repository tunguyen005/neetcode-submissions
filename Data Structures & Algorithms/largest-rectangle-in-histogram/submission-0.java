class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int curHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}
