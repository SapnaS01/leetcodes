class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int gs=0,cs=0;
        for(int i=0;i<gas.length;i++){
            gs+=gas[i];
            cs+=cost[i];    
        }
        if(cs>gs){
                return -1;
        }

        int si=0,currentg=0;

        for(int i=0;i<gas.length;i++){
            currentg+=gas[i]-cost[i];
            if(currentg<0)
            {
                currentg=0;
                si=i+1;
            }
        }

        return si;        
    }
}