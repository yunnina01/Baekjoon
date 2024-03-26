import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> feedList;
    static int[] feedIdx;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        Shark[] sharks = new Shark[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int I = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(S, V, I);
        }

        feedList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            List<Integer> feed = new ArrayList<>();
            Shark now = sharks[i];
            for(int j = 0; j < N; j++) {
                if(i == j)
                    continue;
                Shark next = sharks[j];
                if(now.s < next.s || now.v < next.v || now.i < next.i)
                    continue;
                if(now.s == next.s && now.v == next.v && now.i == next.i && i > j)
                    continue;
                feed.add(j);
            }
            feedList.add(feed);
        }

        feedIdx = new int[N];
        visit = new boolean[N];
        Arrays.fill(feedIdx, -1);
        int res = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 2; j++) {
                Arrays.fill(visit, false);
                if(DFS(i))
                    res++;
            }
        }
        bw.write(N - res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean DFS(int x) {
        if(visit[x])
            return false;
        visit[x] = true;
        for(int now : feedList.get(x)) {
            if(feedIdx[now] == -1 || DFS(feedIdx[now])) {
                feedIdx[now] = x;
                return true;
            }
        }
        return false;
    }
}

class Shark {
    int s, v, i;

    Shark(int s, int v, int i) {
        this.s = s;
        this.v = v;
        this.i = i;
    }
}