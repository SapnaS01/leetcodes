class Solution {
   public int minOperations(String s, int k) {
    int n = s.length();
    int initialZ = (int) s.chars().filter(c -> c == '0').count();
    
    if (initialZ == 0) return 0;
   
    TreeSet<Integer>[] unvisited = new TreeSet[2];
    unvisited[0] = new TreeSet<>();
    unvisited[1] = new TreeSet<>();
    for (int i = 0; i <= n; i++) unvisited[i % 2].add(i);
    
    int[] dist = new int[n + 1];
    Arrays.fill(dist, -1);
    
    Queue<Integer> queue = new LinkedList<>();
    queue.add(initialZ);
    dist[initialZ] = 0;
    unvisited[initialZ % 2].remove(initialZ);
    
    while (!queue.isEmpty()) {
        int z = queue.poll();
        int d = dist[z];
        
        int iMin = Math.max(0, k - (n - z));
        int iMax = Math.min(k, z);
        
        if (iMin > iMax) continue;
        
  
        int zMax = z + k - 2 * iMin;
        int zMin = z + k - 2 * iMax; 
        
        zMin = Math.max(0, zMin);
        zMax = Math.min(n, zMax);
        
        int parity = (z + k) % 2;
        TreeSet<Integer> set = unvisited[parity];
        Integer cur = set.ceiling(zMin);
        
        List<Integer> toRemove = new ArrayList<>();
        while (cur != null && cur <= zMax) {
            toRemove.add(cur);
            cur = set.higher(cur);
        }
        
        for (int nz : toRemove) {
            set.remove(nz);
            dist[nz] = d + 1;
            if (nz == 0) return d + 1;
            queue.add(nz);
        }
    }
    
    return dist[0]; 
}
}