import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        long D = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());

        long res = D / (P * Q) * (P * Q);
        if(res > 0)
            res -= P * Q;
        D -= res;
        if(P < Q) {
            long temp = P;
            P = Q;
            Q = temp;
        }

        long min = Integer.MAX_VALUE;
        for(long i = 0; i * P <= D + P; i++)
            min = Math.min(min, i * P + ((D - P * i + Q - 1) / Q) * Q);
        res += min;

        System.out.println(res);
    }
}