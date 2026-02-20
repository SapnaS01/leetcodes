class Solution {

    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }

        String[] arr = new String[s.length() / 2];
        int count = 0;

        int balance = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                balance++;
            } else {
                balance--;
            }

            if (balance == 0) {
                String inner = makeLargestSpecial(s.substring(start + 1, i));

                arr[count++] = "1" + inner + "0";
                start = i + 1;
            }
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) < 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(arr[i]);
        }

        return result.toString();
    }
}