import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N + M + 1];
            for(int i = 1; i <= N; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            for(int i = 1; i < M; i++)
                arr[i + N] = arr[i];

            int sum = 0;
            int left = 0;
            int right = left + M;
            for(int i = left; i <= right; i++)
                sum += arr[i];
            
            int res = 0;
            if(N == M && sum < K)
                res++;
            else if(N != M) {
                while(left <= N && right < N + M) {
                    if(sum < K)
                        res++;
                    sum -= arr[++left];
                    sum += arr[++right];
                }
            }
            sb.append(res + "\n");
        }
        System.out.print(sb);
    }
}