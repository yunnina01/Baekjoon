import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int g = 0;
        for(int i = 0; i < 2; i++) {
            int r = Integer.parseInt(st.nextToken());
            for(int l = 1;; l <<= 1) {
                if((l & r) != 0) {
                    g ^= l;
                    break;
                }
            }
        }
        System.out.println(g == 0 ? "B player wins" : "A player wins");
    }
}