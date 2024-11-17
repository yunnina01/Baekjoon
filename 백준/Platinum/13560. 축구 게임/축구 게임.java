import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        
        int res = 1;
        for(int i = 1; i <= N; i++) {
            arr[i] += arr[i - 1];
            if(arr[i] >= i * (i - 1) >> 1)
                continue;
            res = -1;
            break;
        }
        if(arr[N] != N * (N - 1) >> 1)
            res = -1;
        System.out.println(res);
    }
}