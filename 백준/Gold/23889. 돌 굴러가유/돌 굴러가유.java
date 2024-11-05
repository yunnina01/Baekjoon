import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] x = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            x[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] startVillage = new int[K];
        for(int i = 0; i < K; i++)
            startVillage[i] = Integer.parseInt(st.nextToken()) - 1;

        List<Stone> stones = new ArrayList<>();
        for(int i = 0; i < K; i++){
            int start = startVillage[i];
            int cnt = 0;
            for(int j = start; j < N; j++) {
                cnt += x[j];
                if(j != N - 1 && i != K - 1 && j + 1 == startVillage[i + 1])
                    break;
            }
            stones.add(new Stone(start, cnt));
        }
        Collections.sort(stones);

        int[] res = new int[M];
        for(int i = 0; i < M; i++)
            res[i] = stones.get(i).idx;
        Arrays.sort(res);

        for(int idx : res)
            sb.append((idx + 1) + "\n");
        System.out.print(sb);
    }
}

class Stone implements Comparable<Stone> {
    int idx, cnt;

    Stone(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Stone o) {
        return o.cnt - this.cnt;
    }
}