class Solution {
    // public static long binToInt(String s) {
    //     long num = 0;
        
    //     for (int i = 0; i < s.length(); i++) {
    //         num = num * 2 + (s.charAt(i) - '0');
    //     }
        
    //     return num;
    // }
    
    // public int numSteps(String s) {
    //     long b = binToInt(s);
    //     int steps = 0;
        
    //     while (b != 1) {
    //         if ((b & 1) != 0) {  
    //             b = b + 1;
    //         } else {             
    //             b = b / 2;
    //         }
    //         steps++;
    //     }
        
    //     return steps;
    // }

    public int numSteps(String s) {
        int steps=0;
        int carry=0;
        for(int i=s.length()-1;i>0;i--){
            int bit=s.charAt(i)-'0';
            if(bit+carry==1){
                steps+=2;
                carry=1;
            }
            else{
                steps+=1;
            }
        }
        return steps+carry;

    }


}