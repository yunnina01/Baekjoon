import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static List<Node> moveList;
    static int[][] board;
    static boolean[][] visit;
    static int N, L, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(move());
    }

    static int move() {
        int res = 0;
        while(true) {
            boolean isMove = false;
            visit = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visit[i][j]) {
                        int sum = BFS(i, j);
                        if(moveList.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove)
                return res;
            res++;
        }
    }

    static int BFS(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        moveList = new ArrayList<>();
        
        queue.offer(new Node(x, y));
        moveList.add(new Node(x, y));
        visit[x][y] = true;

        int sum = board[x][y];
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i], ny = now.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny])
                    continue;
                int diff = Math.abs(board[now.x][now.y] - board[nx][ny]);
                if(L <= diff && R >= diff) {
                    queue.offer(new Node(nx, ny));
                    moveList.add(new Node(nx, ny));
                    sum += board[nx][ny];
                    visit[nx][ny] = true;
                }
            }
        }
        return sum;
    }

    static void changePopulation(int sum) {
        int avg = sum / moveList.size();
        for(Node node : moveList)
            board[node.x][node.y] = avg;
    }
}

class Node {
    int x, y;
    
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}