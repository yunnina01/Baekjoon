import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        long res = 0;
        for(int i = 1; i < (1 << N); i++) {
            long lcm = 1;
            int cnt = 0;
            for(int j = 0; j < N; j++) {
                if(((i >> j) & 1) == 1) {
                    lcm = (lcm * A[j]) / getGCD(lcm, A[j]);
                    cnt++;
                    if(lcm > R)
                        lcm = R + 1;
                }
            }

            if((cnt & 1) == 1)
                res += R / lcm - (L - 1) / lcm;
            else
                res -= R / lcm - (L - 1) / lcm;
        }
        System.out.println(res);
    }

    static long getGCD(long a, long b) {
        return a % b != 0 ? getGCD(b, a % b) : b;
    }
}