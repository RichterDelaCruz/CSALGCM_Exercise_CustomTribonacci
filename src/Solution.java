import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static final long MOD = 1000000007L;
    public static final long[][] base = {{1, 1, 1},
            {1, 0, 0},
            {0, 1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split(" ");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);
        long c = Long.parseLong(parts[2]);
        long i = Long.parseLong(parts[3]);
        System.out.println(compute_ans(a,b,c,i));
    }

    public static long compute_ans(long a, long b, long c, long i){
        // compute for and return answer here
        if(i == 0) {
            return a % MOD;
        } else if(i == 1) {
            return b % MOD;
        } else if(i == 2) {
            return c % MOD;
        } else {
            return matrixVectorMultiplication(a, b, c, matrixExponentiationBySquaring(base, i - 2));
        }
    }

    public static long[][] matrixExponentiationBySquaring(long[][] base, long t) {
        long[][] ans = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        while (t > 0) {
            if (t % 2 == 1) {
                ans = matrixMultiplication(ans, base);
            }
            base = matrixMultiplication(base, base);
            t /= 2;
        }

        return ans;
    }

    public static long matrixVectorMultiplication(long a, long b, long c, long[][] m) {
        long x, y, z;
        x = (m[0][0] * (c%MOD));
        y = (m[0][1] * (b%MOD));
        z = (m[0][2] * (a%MOD));
        return (x + y + z) % MOD;
    }

    public static long[][] matrixMultiplication(long[][] a, long[][] b) {
        long[][] c = new long[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    c[i][j] += (a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}