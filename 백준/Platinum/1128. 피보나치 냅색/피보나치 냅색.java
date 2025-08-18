import java.io.*;
import java.util.*;

public class Main {
    static long[][] things, sums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        things = new long[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            things[i][0] = Long.parseLong(st.nextToken());
            things[i][1] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(things, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return o1[0] == o2[0] ? Long.compare(o1[1], o2[1]) : Long.compare(o1[0], o2[0]);
            }
        });
        
        sums = new long[N][2];
        sums[0][0] = things[0][0];
        sums[0][1] = things[0][1];
        for(int i = 1; i < N; i++) {
            sums[i][0] += sums[i - 1][0] + things[i][0];
            sums[i][1] += sums[i - 1][1] + things[i][1];
        }

        long C = Long.parseLong(br.readLine());

        System.out.println(recur(N - 1, C, false));
    }

    static long recur(int idx, long weight, boolean sameW) {
        if(idx < 0)
            return 0L;
        if(sums[idx][0] <= weight)
            return sums[idx][1];
        if(things[idx][0] <= weight && (!sameW || things[idx + 1][0] != things[idx][0]))
            return Math.max(recur(idx - 1, weight - things[idx][0], false) + things[idx][1], recur(idx - 1, weight, true));
        return recur(idx - 1, weight, true);
    }
}