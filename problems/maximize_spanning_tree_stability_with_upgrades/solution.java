class Solution {
    
    private int[] parent, rank;
    
    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    
    private boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;
        if (rank[px] < rank[py]) { int tmp = px; px = py; py = tmp; }
        parent[py] = px;
        if (rank[px] == rank[py]) rank[px]++;
        return true;
    }
    
    private boolean check(int[][] edges, int n, int k, int target) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        int edgesUsed = 0;
        
       
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < target) return false;        
                if (!union(u, v)) return false;      
                edgesUsed++;
            }
        }
        
       
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s >= target) {
                if (union(u, v)) edgesUsed++;
            }
        }
        
        
        int remaining = k;
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s < target && (long) s * 2 >= target) {
                if (find(u) != find(v) && remaining > 0) {
                    union(u, v);
                    edgesUsed++;
                    remaining--;
                }
            }
        }
        
        return edgesUsed == n - 1;
    }
    
    public int maxStability(int n, int[][] edges, int k) {
        
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges) union(e[0], e[1]);
        int root = find(0);
        for (int i = 1; i < n; i++) {
            if (find(i) != root) return -1;
        }
        

        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges) {
            if (e[3] == 1 && !union(e[0], e[1])) return -1;
        }
        

        int lo = 1, hi = 0;
        for (int[] e : edges) hi = Math.max(hi, e[2]);
        hi *= 2; 
        
        int result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(edges, n, k, mid)) {
                result = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return result;
    }
}