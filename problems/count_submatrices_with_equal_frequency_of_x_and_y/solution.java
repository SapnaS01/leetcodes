class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        int[][] val  = new int[rows + 1][cols + 1];
        int[][] xcnt = new int[rows + 1][cols + 1];

        int result = 0;

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                char cell = grid[r - 1][c - 1];

                int v = (cell == 'X') ? 1 : (cell == 'Y') ? -1 : 0;
                int x = (cell == 'X') ? 1 : 0;

 
                val[r][c]  = v + val[r-1][c] + val[r][c-1] - val[r-1][c-1];
                xcnt[r][c] = x + xcnt[r-1][c] + xcnt[r][c-1] - xcnt[r-1][c-1];

                if (val[r][c] == 0 && xcnt[r][c] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}