import java.io.*;
import java.util.*;

public class Main{
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[K];
        for(int i = 2; i < K; i++)
            arr[i] = (arr[i - 1] + K) % i;
        System.out.println(K == 1 ? N : joseph(N, K) + 1);
    }

    static long joseph(long n, int k) {
        if(n < k)
            return arr[(int)n];
        long next = joseph(n - (n / k), k) - n % k;
        return next < 0 ? next + n : next + next / (k - 1);
    }
}