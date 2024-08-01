import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 1_000_000_000_000_000_000L;
    static final String IMPOSSIBLE = "Unknwon Number";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        long from = Long.parseLong(st.nextToken());
        long to = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long x = Math.abs(Long.parseLong(st.nextToken()));
        long y = Long.parseLong(st.nextToken());

        System.out.println(guess(Math.min(from, to), Math.max(from, to), x, y));
    }

    static String guess(long from, long to, long x, long y) {
        if(y < 0 || y >= x)
            return IMPOSSIBLE;

        boolean check = (x << 1L) <= (to - from);
        if(check)
            return IMPOSSIBLE;

        long mod = (from % x + x) % x;
        if(mod != y) {
            from += y - mod;
            if(mod > y)
                from += x;
        }

        long val = INF;
        int cnt = 0;
        for(long i = from; i <= to; i += x) {
            if((i % x + x) % x == y){
                val = i;
                cnt++;
            }
            if(cnt > 1)
                return IMPOSSIBLE;
        }
        return val == INF ? IMPOSSIBLE : (val + "");
    }
}