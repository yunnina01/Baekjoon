import java.io.*;
import java.util.*;

public class Main {
    static Pair[] pairs;
    static int N, K, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st, st2;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        pairs = new Pair[N];
        int[] A = new int[N];
        int[] P = new int[N];

        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st2.nextToken());
            pairs[i] = new Pair(A[i], P[i]);
        }

        Arrays.sort(pairs);
        backtrack(0, K, 0, 0);
        System.out.println(res);
    }

    static void backtrack(int idx, int k, int acc, int save) {
        res = Math.max(res, save);
        if(idx == N)
            return;
        if(k - pairs[idx].a - acc < 0)
            return;

        backtrack(idx + 1, k, acc, save);
        backtrack(idx + 1, k - acc - pairs[idx].a, acc + pairs[idx].a, save + pairs[idx].b);
    }
}

class Pair implements Comparable<Pair>{
    int a, b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.a == o.a)
            return this.b - o.b;
        return this.a - o.a;
    }
}