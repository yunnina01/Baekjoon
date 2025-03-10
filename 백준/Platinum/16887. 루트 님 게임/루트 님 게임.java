import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int g = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            long A = Long.parseLong(st.nextToken());
            g ^= grundy(A);
        }
        System.out.println(g == 0 ? "cubelover" : "koosaga");
    }

    static int grundy(long num) {
        if(num < 4L)
            return 0;
        if(num < 16L)
            return 1;
        if(num < 82L)
            return 2;
        if(num < 6724L)
            return 0;
        if(num < 50625L)
            return 3;
        if(num < 2562890625L)
            return 1;
        return 2;
    }
}