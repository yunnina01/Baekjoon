import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        long sum = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        long res = 0;
        if(sum > M) {
            long lack = sum - M;
            Arrays.sort(arr);
            for(int i = 0; i < N; i++) {
                long notReceive = Math.min(arr[i], lack / (N - i));
                lack -= notReceive;
                res += notReceive * notReceive;
            }
            res %= (long)Math.pow(2, 64);
        }
        System.out.println(res);
    }
}