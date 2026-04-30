class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

       
        int[][][] dp = new int[m][n][k + 1];
        
 
        for (int[][] layer : dp)
            for (int[] row : layer)
                java.util.Arrays.fill(row, -1);


        dp[0][0][0] = 0;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cellCost  = (grid[i][j] == 0) ? 0 : 1;
                int cellScore = grid[i][j];

                if (i == 0 && j == 0) continue;

                if (i > 0) {
                    for (int c = cellCost; c <= k; c++) {
                        int prevCost = c - cellCost;
                        if (dp[i - 1][j][prevCost] != -1) {
                            dp[i][j][c] = Math.max(
                                dp[i][j][c],
                                dp[i - 1][j][prevCost] + cellScore
                            );
                        }
                    }
                }

               
                if (j > 0) {
                    for (int c = cellCost; c <= k; c++) {
                        int prevCost = c - cellCost;
                        if (dp[i][j - 1][prevCost] != -1) {
                            dp[i][j][c] = Math.max(
                                dp[i][j][c],
                                dp[i][j - 1][prevCost] + cellScore
                            );
                        }
                    }
                }
            }
        }

 
        int ans = -1;
        for (int c = 0; c <= k; c++) {
            if (dp[m - 1][n - 1][c] != -1) {
                ans = Math.max(ans, dp[m - 1][n - 1][c]);
            }
        }

        return ans;
    }
}