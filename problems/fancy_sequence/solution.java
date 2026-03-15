class Fancy {
    private static final long MOD = 1_000_000_007L;
    private long[] seq;
    private int size;
    private long globalMult; 
    private long globalAdd; 

    public Fancy() {
        seq = new long[100_001];
        size = 0;
        globalMult = 1;
        globalAdd = 0;
    }

    private long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    private long modInverse(long a) {
        return modPow(a, MOD - 2, MOD);
    }

    public void append(int val) {
        long normalized = ((val - globalAdd) % MOD + MOD) % MOD;
        normalized = normalized * modInverse(globalMult) % MOD;
        seq[size++] = normalized;
    }

    public void addAll(int inc) {
        globalAdd = (globalAdd + inc) % MOD;
    }

    public void multAll(int m) {
       
        globalMult = globalMult * m % MOD;
        globalAdd = globalAdd * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= size) return -1;

        long result = (seq[idx] * globalMult % MOD + globalAdd) % MOD;
        return (int) result;
    }
}