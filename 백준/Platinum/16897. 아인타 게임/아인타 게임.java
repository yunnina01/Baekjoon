import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int div = Math.min(N, M);
            if(K == 1)
                System.out.println((N % 2 == M % 2 ? (N + 1) % 2 * 2 : 1 + (Math.min(N, M) + 1) % 2 * 2) == 0 ? "cubelover" : "koosaga");
            else
                System.out.println((div % (K + 1) != 0 && (div / (K + 1) + N + M) % 2 == 0) ? "cubelover" : "koosaga");
        }
    }
}