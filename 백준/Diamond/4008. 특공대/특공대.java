import java.io.*;
import java.util.*;

public class Main {
    static long[] dp, x;
    static long a, b, c;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = new long[n + 1];
        for(int i = 1 ; i <= n ; i++) {
            x[i] = Integer.parseInt(st.nextToken());
            x[i] += x[i - 1];
        }

        LinkedList<Integer> polygon = new LinkedList<>();
        dp = new long[n + 1];
        polygon.offerLast(0);

        for(int i = 1 ; i <= n ; i++) {
            while(polygon.size() > 1 && intersect(polygon.peekFirst(), polygon.get(1)) < x[i])
                polygon.pollFirst();

            int p = polygon.peekFirst();
            dp[i] = x[p] * x[i] * -2 * a + (x[p] * x[p]) * a - x[p] * b + dp[p] + evaluate(i);
            while(polygon.size() > 1 && intersect(i, polygon.get(polygon.size() - 2)) < intersect(polygon.peekLast(), polygon.get(polygon.size() - 2)))
                polygon.pollLast();
            polygon.offerLast(i);
        }
        System.out.println(dp[n]);
    }

    static double intersect(int i, int j) {
        return ((x[j] * x[j] - x[i] * x[i]) * a - (x[j] - x[i]) * b + dp[j] - dp[i]) / ((x[j] - x[i]) * 2.0 * a);
    }

    static long evaluate(int n) {
        return (x[n] * x[n]) * a + x[n] * b + c;
    }
}