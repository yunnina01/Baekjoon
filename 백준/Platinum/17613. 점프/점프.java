import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            sb.append(recur(x, y) + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static long recur(long left, long right) {
        if((left | right) == 0)
            return 0L;
        if(dp.containsKey((left << 32) + right))
            return dp.get((left << 32) + right);

        long ret = 0;
        for(int i = 1; i <= 32; i++) {
            long cl = Math.max(left, (1L << i) - 1);
            long cr = Math.min(right, (1L << (i + 1)) - 2);
            if(cl > cr)
                continue;
            ret = Math.max(ret, i + recur(cl - (1L << i) + 1, cr - (1L << i) + 1));
        }
        dp.put((left << 32) + right, ret);
        return ret;
    }
}