class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {

        double[][] tower = new double[101][101];
        tower[0][0] = poured;

        for (int row = 0; row < query_row; row++) {
            for (int glass = 0; glass <= row; glass++) {

                double overflow = Math.max(0.0, tower[row][glass] - 1.0);

                tower[row + 1][glass]     += overflow / 2;
                tower[row + 1][glass + 1] += overflow / 2;
            }
        }

        return Math.min(1.0, tower[query_row][query_glass]);
    }
}