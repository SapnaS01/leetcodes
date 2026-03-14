class Solution {

    List<String> result = new ArrayList<>();

    public String getHappyString(int n, int k) {
        backtrack("", n, k);

        if (result.size() < k) {
            return "";
        }

        return result.get(k - 1);
    }

    private void backtrack(String current, int n, int k) {

        if (result.size() >= k) return;

        if (current.length() == n) {
            result.add(current);
            return;
        }

        char[] chars = {'a', 'b', 'c'};

        for (char ch : chars) {

            if (current.length() > 0 && current.charAt(current.length() - 1) == ch) {
                continue;
            }

            backtrack(current + ch, n, k);
        }
    }
}