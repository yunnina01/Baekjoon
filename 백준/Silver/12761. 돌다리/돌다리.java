import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] amount = {1, -1, A, -A, B, -B, A, B};
        Queue<int[]> queue = new ArrayDeque<>();
        int[] visit = new int[100001];
        queue.offer(new int[] {N, 0});
        int res = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if(now[0] == M) {
                res = now[1];
                break;
            }

            for(int i = 0; i < 8; i++) {
                int next = now[0] + amount[i];
                if(i >= 6)
                    next = now[0] * amount[i];
                if(next < 0 || next > 100000 || visit[next] == 1)
                    continue;

                queue.offer(new int[] {next, now[1] + 1});
                visit[next] = 1;
            }
        }
        System.out.println(res);
    }
}