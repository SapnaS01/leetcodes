// class Solution {

//     class SegmentTree {
//         int[] tree;
//         int size;

//         SegmentTree(int[] data) {
//             size = data.length;
//             tree = new int[size * 4];
//             build(data, 0, size - 1, 0);
//         }

//         void build(int[] data, int l, int r, int pos) {
//             if (l == r) {
//                 tree[pos] = data[l];
//                 return;
//             }
//             int mid = (l + r) / 2;
//             build(data, l, mid, 2 * pos + 1);
//             build(data, mid + 1, r, 2 * pos + 2);
//             tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
//         }

//         int query(int ql, int qr) {
//             return queryUtil(0, size - 1, ql, qr, 0);
//         }

//         int queryUtil(int l, int r, int ql, int qr, int pos) {
//             if (ql > r || qr < l) return Integer.MAX_VALUE;
//             if (ql <= l && r <= qr) return tree[pos];
//             int mid = (l + r) / 2;
//             return Math.min(queryUtil(l, mid, ql, qr, 2 * pos + 1),
//                             queryUtil(mid + 1, r, ql, qr, 2 * pos + 2));
//         }

//         void update(int index, int value) {
//             updateUtil(0, size - 1, index, value, 0);
//         }

//         void updateUtil(int l, int r, int index, int value, int pos) {
//             if (l == r) {
//                 tree[pos] = value;
//                 return;
//             }
//             int mid = (l + r) / 2;
//             if (index <= mid) updateUtil(l, mid, index, value, 2 * pos + 1);
//             else updateUtil(mid + 1, r, index, value, 2 * pos + 2);
//             tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
//         }
//     }

//     public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
//         int n = fruits.length;

//         int[][] sortedBaskets = new int[n][2];
//         for (int i = 0; i < n; i++) {
//             sortedBaskets[i][0] = baskets[i];
//             sortedBaskets[i][1] = i;
//         }
//         Arrays.sort(sortedBaskets, Comparator.comparingInt(a -> a[0]));

//         int[] originalIndices = new int[n];
//         for (int i = 0; i < n; i++) {
//             originalIndices[i] = sortedBaskets[i][1];
//         }

//         SegmentTree segmentTree = new SegmentTree(originalIndices);

//         int unplaced = 0;

//         for (int fruit : fruits) {
//             int left = 0, right = n - 1;
//             int idx = n;

//             while (left <= right) {
//                 int mid = (left + right) / 2;
//                 if (sortedBaskets[mid][0] >= fruit) {
//                     idx = mid;
//                     right = mid - 1;
//                 } else {
//                     left = mid + 1;
//                 }
//             }

//             if (idx == n) {
//                 unplaced++;
//                 continue;
//             }

//             int minOriginalIdx = segmentTree.query(idx, n - 1);
//             if (minOriginalIdx == Integer.MAX_VALUE) {
//                 unplaced++;
//             } else {
//                 int useIndex = -1;
//                 for (int i = idx; i < n; i++) {
//                     if (sortedBaskets[i][1] == minOriginalIdx) {
//                         useIndex = i;
//                         break;
//                     }
//                 }
//                 segmentTree.update(useIndex, Integer.MAX_VALUE);
//             }
//         }

//         return unplaced;
//     }
// }

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        
        // Create array of basket info (capacity, original_index) and sort by capacity
        int[][] basketInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            basketInfo[i][0] = baskets[i]; // capacity
            basketInfo[i][1] = i;          // original index
        }
        Arrays.sort(basketInfo, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Create mapping from original index to sorted position for O(1) lookup
        int[] indexToPos = new int[n];
        for (int i = 0; i < n; i++) {
            indexToPos[basketInfo[i][1]] = i;
        }
        
        // Segment tree to track minimum available index for each capacity range
        SegmentTree segTree = new SegmentTree(n, basketInfo, indexToPos);
        
        int unplaced = 0;
        
        // Process each fruit
        for (int fruitQty : fruits) {
            int pos = segTree.findAndUseLeftmostAvailable(fruitQty);
            if (pos == -1) {
                unplaced++;
            }
        }
        
        return unplaced;
    }
    
    // Optimized Segment Tree class
    class SegmentTree {
        private int[] tree;
        private int size;
        private int[][] basketInfo;
        private int[] indexToPos;
        
        public SegmentTree(int size, int[][] basketInfo, int[] indexToPos) {
            this.size = size;
            this.basketInfo = basketInfo;
            this.indexToPos = indexToPos;
            this.tree = new int[4 * size];
            Arrays.fill(tree, Integer.MAX_VALUE);
            build(0, 0, size - 1);
        }
        
        private void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = basketInfo[start][1]; // original index
            } else {
                int mid = (start + end) / 2;
                build(2 * node + 1, start, mid);
                build(2 * node + 2, mid + 1, end);
                tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }
        
        private int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return Integer.MAX_VALUE;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            
            int mid = (start + end) / 2;
            int leftMin = query(2 * node + 1, start, mid, l, r);
            int rightMin = query(2 * node + 2, mid + 1, end, l, r);
            return Math.min(leftMin, rightMin);
        }
        
        private void update(int node, int start, int end, int idx) {
            if (start == end) {
                tree[node] = Integer.MAX_VALUE; // Mark as used
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(2 * node + 1, start, mid, idx);
                } else {
                    update(2 * node + 2, mid + 1, end, idx);
                }
                tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }
        
        public int findAndUseLeftmostAvailable(int capacity) {
            // Binary search for first basket with capacity >= required
            int left = 0, right = size - 1;
            int firstValid = -1;
            
            while (left <= right) {
                int mid = (left + right) / 2;
                if (basketInfo[mid][0] >= capacity) {
                    firstValid = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            if (firstValid == -1) {
                return -1; // No basket with sufficient capacity
            }
            
            // Query segment tree for minimum index in valid range
            int minOriginalIdx = query(0, 0, size - 1, firstValid, size - 1);
            
            if (minOriginalIdx == Integer.MAX_VALUE) {
                return -1; // No available basket
            }
            
            // Use precomputed mapping to get position in O(1)
            int pos = indexToPos[minOriginalIdx];
            
            // Mark this basket as used
            update(0, 0, size - 1, pos);
            
            return pos;
        }
    }
}