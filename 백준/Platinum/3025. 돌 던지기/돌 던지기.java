import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static Pair[] memory;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        for(int i = 0; i < R; i++)
            map[i] = br.readLine().toCharArray();

        memory = new Pair[C];
        for (int i = 0; i < C; i++) {
            memory[i] = new Pair();
            memory[i].col[0] = i;
            memory[i].update();
        }

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int pos = Integer.parseInt(br.readLine()) - 1;

            memory[pos].insert();
            for(int j = 0; j < C; j++)
                memory[j].update();
        }

        for(int i = 0; i < R; i++)
            sb.append(map[i]).append("\n");
        System.out.print(sb);
    }

    static class Pair {
        int r;
        int[] col;

        Pair() {
            this.r = 1;
            this.col = new int[30000];
        }

        void insert() {
            map[r - 1][col[r - 1]] = 'O';
        }

        void update() {
            while(true) {
                int now = col[r - 1];
                if(r > 1 && map[r - 1][now] != '.')
                    r--;
                else if(r == R || map[r][now] == 'X')
                    break;
                else if(map[r][now] == '.') {
                    col[r] = now;
                    r++;
                } else {
                    if(now > 0 && map[r][now - 1] == '.' && map[r - 1][now - 1] == '.') {
                        col[r] = now - 1;
                        r++;
                    } else if(now + 1 < C && map[r][now + 1] == '.' && map[r - 1][now + 1] == '.') {
                        col[r] = now + 1;
                        r++;
                    } else
                        break;
                }
            }
        }
    }
}