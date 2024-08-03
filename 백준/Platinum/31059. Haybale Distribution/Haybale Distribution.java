import java.io.*;
import java.util.*;

public class Main {
    static long[] prefix, suffix;
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] barns = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            barns[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(barns);

        prefix = new long[1000002];
        int idx = 0;
        for(int i = 0; i < 1000001; i++) {
            if(i != 0)
                prefix[i] = prefix[i - 1] + idx;
            while(idx < N && barns[idx] == i)
                idx++;
        }

        suffix = new long[1000002];
        idx = N - 1;
        for(int i = 1000000; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + N - idx -1;
            while(idx >= 0 && barns[idx] == i)
                idx--;
        }

        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(0, 1000000) + "\n");
        }
        System.out.print(sb);
    }

    static long binarySearch(int start, int end) {
        long ret = Long.MAX_VALUE;
        while(start <= end) {
            int mid = (start + end) / 2;
            long cost1 = getCost(mid);
            long cost2 = getCost(mid + 1);
            if(cost1 > cost2)
                start = mid + 1;
            else
                end = mid - 1;
            ret = Math.min(ret, cost1);
        }
        return ret;
    }

    static long getCost(int y) {
        if(y < 0 || y > 1000000)
            return Long.MAX_VALUE;
        return a * prefix[y] + b * suffix[y];
    }
}