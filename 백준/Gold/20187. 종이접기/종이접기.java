import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static char[] arr;
    static int k, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        arr = new char[2 * k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2 * k; i++)
            arr[i] = st.nextToken().charAt(0);

        H = Integer.parseInt(br.readLine());

        int len = (int)Math.pow(2, k);
        map = new int[len][len];
        recur(0, 0, len, len, 0);

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++)
                sb.append(map[i][j] + " ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void recur(int y, int x, int w, int h, int idx) {
        if(idx == k * 2) {
            map[y][x] = H;
            return;
        }

        char now = arr[idx];
        if(now == 'U') {
            recur(y, x, w, h / 2, idx + 1);
            y += h / 2;
            for(int i = 0; i < h / 2; i++) {
                for(int j = 0; j < w; j++)
                    map[y + i][x + j] = getUD(map[y + i - (i * 2 + 1)][x + j]);
            }
        } else if(now == 'D') {
            recur(y + h / 2, x, w, h / 2, idx + 1);
            for(int i = 0; i < h / 2; i++) {
                for(int j = 0; j < w; j++)
                    map[y + i][x + j] = getUD(map[y + i + ((h / 2 - i) * 2 - 1)][x + j]);
            }
        } else if(now == 'R') {
            recur(y, x + w / 2, w / 2, h, idx + 1);
            for(int i = 0; i < w / 2; i++) {
                for(int j = 0; j < h; j++)
                    map[y + j][x + i] = getRL(map[y + j][x + i + ((w / 2 - i) * 2 - 1)]);
            }
        } else {
            recur(y, x, w / 2, h, idx + 1);
            x += w / 2;
            for(int i = 0; i < w / 2; i++) {
                for(int j = 0; j < h; j++)
                    map[y + j][x + i] = getRL(map[y + j][x + i - (i * 2 + 1)]);
            }
        }
    }

    static int getUD(int num) {
        return (num + 2) % 4;
    }

    static int getRL(int num) {
        return num % 2 == 0 ? num + 1 : num - 1;
    }
}