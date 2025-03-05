import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int W = Integer.parseInt(br.readLine());

        while(W-- > 0) {
            st = new StringTokenizer(br.readLine());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            
            long gcd = GCD(p, q);
            sb.append((p * n * (n + 1) >> 1) - q * recur(p / gcd, q / gcd, n) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static long GCD(long a, long b) {
        return b > 0 ? GCD(b, a % b) : a;
    }

    static long recur(long a, long b, long c) {
        if(a == 0)
            return a;
        return a < b ? (a * c / b) * c + c / b - recur(b, a, a * c / b) : ((a / b) * c * (c + 1) >> 1) + recur(a % b, b, c);
    }
}