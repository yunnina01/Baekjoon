import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] map;
    static int res = 0;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R][C];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if(d == 1)
                d = 0;
            else if(d == 4)
                d = 1;
            map[r][c] = new Shark(r, c, s, d, z);
        }

        for(int i = 0; i < C; i++) {
            for(int j = 0; j < R; j++) {
                if(map[j][i] != null) {
                    res += map[j][i].z;
                    map[j][i] = null;
                    break;
                }
            }
            move();            
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void move() {
        Queue<Shark> q = new LinkedList<>();
        for(int j = 0; j < R; j++) {
            for(int k = 0; k < C; k++) {
                if(map[j][k] != null)
                    q.offer(new Shark(j, k, map[j][k].s, map[j][k].d, map[j][k].z));
            }
        }

        map = new Shark[R][C];
        while(!q.isEmpty()) {
            Shark shark = q.poll();
            int speed = shark.s;
            if(shark.d == 0 || shark.d == 2)
                speed %= (R - 1) * 2;
            else
                speed %= (C - 1) * 2;
    
            for(int j = 0; j < speed; j++) {
                int nr = shark.r + dr[shark.d], nc = shark.c + dc[shark.d];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    shark.r -= dr[shark.d];
                    shark.c -= dc[shark.d];
                    shark.d = (shark.d + 2) % 4;
                    continue;
                }
                shark.r = nr;
                shark.c = nc;
            }

            if(map[shark.r][shark.c] != null) {
                if(map[shark.r][shark.c].z < shark.z)
                    map[shark.r][shark.c] = shark;
            } else
                map[shark.r][shark.c] = shark;
        }
    }
}

class Shark {
    int r, c, s, d, z;

    Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}