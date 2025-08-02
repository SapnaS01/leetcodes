class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : basket1) freq.put(num, freq.getOrDefault(num, 0) + 1);
        for (int num : basket2) freq.put(num, freq.getOrDefault(num, 0) + 1);

        for (int count : freq.values()) {
            if (count % 2 != 0) return -1;
        }

        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();
        for (int num : basket1) count1.put(num, count1.getOrDefault(num, 0) + 1);
        for (int num : basket2) count2.put(num, count2.getOrDefault(num, 0) + 1);

        List<Integer> surplus1 = new ArrayList<>();
        List<Integer> surplus2 = new ArrayList<>();
        for (int num : freq.keySet()) {
            int diff = count1.getOrDefault(num, 0) - count2.getOrDefault(num, 0);
            if (diff > 0) {
                for (int i = 0; i < diff / 2; i++) surplus1.add(num);
            } else if (diff < 0) {
                for (int i = 0; i < -diff / 2; i++) surplus2.add(num);
            }
        }

        Collections.sort(surplus1);
        surplus2.sort(Collections.reverseOrder());

        int minElement = Integer.MAX_VALUE;
        for (int num : basket1) minElement = Math.min(minElement, num);
        for (int num : basket2) minElement = Math.min(minElement, num);

        long cost = 0;
        for (int i = 0; i < surplus1.size(); i++) {
            int a = surplus1.get(i);
            int b = surplus2.get(i);
            cost += Math.min(Math.min(a, b), 2 * minElement);
        }

        return cost;
    }
}