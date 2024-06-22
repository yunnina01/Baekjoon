import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static HashMap<Long, Integer> diffs;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        diffs = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        long preDay = Long.parseLong(st.nextToken());
        for (int i = 1; i < N; i++) {
            long day = Long.parseLong(st.nextToken());
            long diff = day - preDay;

            if(!diffs.containsKey(diff))
                diffs.put(diff, 1);
            else
                diffs.put(diff, diffs.get(diff) + 1);
            preDay = day;
        }
        diffs.put((long)INF, 1);

        long start = 1;
        long end = INF;
        while(start < end) {
            long mid = (start + end) >> 1;
            if(possible(mid))
                end = mid;
            else
                start = mid + 1;
        }
        System.out.println(start);
    }

    static boolean possible(long cost) {
        long sum = 0;
        for(long diff : diffs.keySet()) {
            long num = Math.min(cost, diff);
            sum += num * (cost + (cost - num + 1)) / 2 * diffs.get(diff);
            if(sum >= K)
                return true;
        }
        return false;
    }
}