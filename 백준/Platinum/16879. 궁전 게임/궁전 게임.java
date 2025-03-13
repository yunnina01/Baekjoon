import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int g = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            g ^= grundy(x, y);
        }
        System.out.println(g == 0 ? "cubelover" : "koosaga");
    }

    static int grundy(int a, int b) {
        return ((a / 3) ^ (b / 3)) * 3 + (a + b) % 3;
    }
}