import java.io.*;
import java.util.*;

public class Main {
    static List<Problem> pList;
    static long[][] dp;
    static boolean[][] visit;
    static int N, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        pList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            pList.add(new Problem(Integer.parseInt(st.nextToken())));

        st = new StringTokenizer(br.readLine());
        for(Problem p : pList)
            p.P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(Problem p : pList)
            p.setR(Integer.parseInt(st.nextToken()));

        Collections.sort(pList);
        
        dp = new long[N + 1][T + 1];
        visit = new boolean[N + 1][T + 1];
        System.out.println(recur(0, 0));
    }

    static long recur(int idx, int t) {
        if(idx == N)
            return 0;
        if(!visit[idx][t]) {
            visit[idx][t] = true;
            Problem p = pList.get(idx);
            if(t + p.R <= T)
                dp[idx][t] = Math.max(dp[idx][t], recur(idx + 1, t + (int)p.R) + p.M - p.P * (t + p.R));
            dp[idx][t] = Math.max(dp[idx][t], recur(idx + 1, t));
        }
        return dp[idx][t];
    }
}

class Problem implements Comparable<Problem> {
    long M, P, R;
    double comp;

    Problem(int m) {
        M = m;
    }

    void setR(int r) {
        R = r;
        comp = 1.0 * R / P;
    }

    @Override
    public int compareTo(Problem o) {
        return Double.compare(comp, o.comp);
    }
}