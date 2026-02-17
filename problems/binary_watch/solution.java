class Solution {

    public static int countBits(int n){
        int c=0;
        while(n>0){
            c+=(n&1);
            n=n>>1;
        }
        return c;
    }
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                
                // int totalBits = Integer.bitCount(hour) + Integer.bitCount(minute);
                int totalBits=countBits(hour)+ countBits(minute);
                
                if (totalBits == turnedOn) {
                   
                    String time = hour + ":" + 
                                  (minute < 10 ? "0" + minute : minute);
                    result.add(time);
                }
            }
        }
        
        return result;
    }
}
