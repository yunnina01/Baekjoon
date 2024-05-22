import java.io.*;
import java.util.*;

public class Main {
    static long[] T, V;
    static long res = -1;
    static int N, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        T = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            T[i] = Integer.parseInt(st.nextToken());

        V = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            V[i] = Integer.parseInt(st.nextToken());

        recur(1, N, 1, N);
        System.out.println(res);
    }

    static void recur(int left, int right, int cl, int cr) {
        if(left > right)
            return;
        int mid = (left + right) >> 1;
        int tmp = Math.max(cl, mid - D);
        for(int i = tmp; i <= Math.min(cr, mid); i++) {
            if((mid - tmp) * T[mid] + V[tmp] >= (mid - i) * T[mid] + V[i])
                continue;
            tmp = i;
        }
        res = Math.max(res, (mid - tmp) * T[mid] + V[tmp]);
        recur(left, mid - 1, cl, tmp);
        recur(mid + 1, right, tmp, cr);
    }
}