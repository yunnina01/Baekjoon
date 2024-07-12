import java.io.*;
import java.util.*;

public class Main {
    static List<Point> milk;
    static int[] homeDist;
    static boolean[] eat;
    static int N, H, milkCount, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        milk = new ArrayList<>();
        Point home = new Point(0, 0);
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int cell = Integer.parseInt(st.nextToken());
                if(cell == 1) {
                    home.x = i;
                    home.y = j;
                } else if(cell == 2) {
                    milk.add(new Point(i, j));
                    milkCount++;
                }
            }
        }

        homeDist = new int[milkCount];
        for(int i = 0; i < milkCount; i++) {
            Point now = milk.get(i);
            homeDist[i] = getDist(home.x, home.y, now.x, now.y);
        }

        eat = new boolean[milkCount];
        recur(home.x, home.y, M, 0);
        System.out.println(res);
    }

    static void recur(int x, int y, int move, int eatCount) {
        if(res == milkCount)
            return;

        for(int i = 0; i < milk.size(); i++) {
            Point now = milk.get(i);
            int dist = getDist(x, y, now.x, now.y);
            if(dist > move || eat[i])
                continue;

            int nextMove = move - dist + H;
            if(nextMove >= homeDist[i] && eatCount + 1 > res)
                res = eatCount + 1;
            eat[i] = true;
            recur(now.x, now.y, nextMove, eatCount + 1);
            eat[i] = false;
        }
    }

    static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}