import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static final int INF = 200_001;
    static final int SIZE = INF << 1;
    static Star[] stars;
    static long[] tree = new long[SIZE];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        stars = new Star[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + INF;
            int y = Integer.parseInt(st.nextToken()) + INF;
            stars[i] = new Star(x, y);
            update(stars[i].x, 1);
        }
        Arrays.sort(stars);

        long res = 0;
        int target = -SIZE;
        for(int i = 0; i < N; i++) {
            if(target != stars[i].y) {
                target = stars[i].y;
                for(int j = i; j < N; j++) {
                    if(target != stars[j].y)
                        break;
                    update(stars[j].x, -1);
                }
            }

            long prev = sum(stars[i].x - 1);
            long post = sum(SIZE - 1) - sum(stars[i].x);
            res += (prev % MOD) * (post % MOD);
            res %= MOD;
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static void update(int index, int value) {
        while(index < SIZE) {
            tree[index] += value;
            index += (index & -index);
        }
    }

    private static long sum(int index) {
        long sum = 0;
        while(index > 0) {
            sum += tree[index];
            index -= (index & -index);
        }
        return sum;
    }
}

class Star implements Comparable<Star>{
    int x, y;

    Star(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Star o) {
        if(this.y == o.y)
            return this.x - o.x;
        else
            return this.y - o.y;
    }
}