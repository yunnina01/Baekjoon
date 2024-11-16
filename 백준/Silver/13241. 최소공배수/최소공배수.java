import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long gcd = GCD(Math.max(A, B), Math.min(A, B));
        System.out.println(A * B / gcd);
    }

    static long GCD(long a, long b) {
        if(b == 0)
            return a;
        return GCD(b, a % b);
    }
}