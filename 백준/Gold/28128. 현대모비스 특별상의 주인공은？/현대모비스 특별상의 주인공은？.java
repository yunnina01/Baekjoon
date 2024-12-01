import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {2, 0}, {0, 1}, {0, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> names = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                String name = st.nextToken();
                if(!hashMap.containsKey(name)) {
                    hashMap.put(name, hashMap.size());
                    names.add(name);
                }
                board[i][j] = hashMap.get(name);
            }
        }
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[hashMap.size() + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visit[board[i][j]])
                    continue;

                for(int[] dir : DIRECTIONS) {
                    int nr = i + dir[0];
                    int nc = j + dir[1];
                    if(nr < N && nc < M && board[i][j] == board[nr][nc] && !visit[board[i][j]]) {
                        pq.offer(names.get(board[i][j]));
                        visit[board[i][j]] = true;
                        break;
                    }
                }
            }
        }
        
        if(pq.isEmpty())
            sb.append("MANIPULATED\n");
        else {
            while(!pq.isEmpty())
                sb.append(pq.poll() + "\n");
        }
        System.out.print(sb);
    }
}