import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        bw.write(bitmasking(B) - bitmasking(A - 1) + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static long bitmasking(long x) {
        if(x <= 0)
            return 0;
        long bit = 0;
        while((1L << (bit + 1)) - 1 <= x)
            bit++;
        return bit * (1L << (bit - 1)) + x - (1L << bit) + 1 + bitmasking(x - (1L << bit));
    }
}