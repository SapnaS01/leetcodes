class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> top3 = new TreeSet<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                addToTop3(top3, grid[r][c]);

                for (int k = 1; r - k >= 0 && r + k < m && c - k >= 0 && c + k < n; k++) {
                    int sum = getRhombusSum(grid, r, c, k);
                    addToTop3(top3, sum);
                }
            }
        }
        

        int[] result = new int[top3.size()];
        int idx = 0;
        for (int val : top3.descendingSet()) {
            result[idx++] = val;
        }
        return result;
    }
    
    private int getRhombusSum(int[][] grid, int r, int c, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += grid[r - k + i][c + i];
        }
        for (int i = 0; i < k; i++) {
            sum += grid[r + i][c + k - i];
        }
        for (int i = 0; i < k; i++) {
            sum += grid[r + k - i][c - i];
        }
       
        for (int i = 0; i < k; i++) {
            sum += grid[r - i][c - k + i];
        }
   
        
        return sum;
    }
    
    private void addToTop3(TreeSet<Integer> top3, int val) {
        top3.add(val);

        if (top3.size() > 3) {
            top3.pollFirst(); 
        }
    }
}