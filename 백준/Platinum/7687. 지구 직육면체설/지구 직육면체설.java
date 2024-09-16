import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            long lx = Long.parseLong(st.nextToken());
            long ly = Long.parseLong(st.nextToken());
            long lz = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());

            if((lx | ly | lz | x | y | z) == 0)
                break;

            if(x == 0 || y == 0 || z == 0)
                sb.append(x * x + y * y + z * z + "\n");
            else {
                long res = Long.MAX_VALUE;
                if(lx == x) {
                    res = Math.min(res, (x + y) * (x + y) + z * z);
                    res = Math.min(res, (x + z) * (x + z) + y * y);
                    res = Math.min(res, (lz + y) * (lz + y) + (lx + lz - z) * (lx + lz - z));
                    res = Math.min(res, (ly + z) * (ly + z) + (lx + ly - y) * (lx + ly - y));
                }
                if(ly == y) {
                    res = Math.min(res, (x + y) * (x + y) + z * z);
                    res = Math.min(res, (y + z) * (y + z) + x * x);
                    res = Math.min(res, (lz + x) * (lz + x) + (ly + lz - z) * (ly + lz - z));
                    res = Math.min(res, (lx + z) * (lx + z) + (lx + ly - x) * (lx + ly - x));
                }
                if(lz == z) {
                    res = Math.min(res, (z + y) * (z + y) + x * x);
                    res = Math.min(res, (x + z) * (x + z) + y * y);
                    res = Math.min(res, (lx + y) * (lx + y) + (lx + lz - x) * (lx + lz - x));
                    res = Math.min(res, (ly + x) * (ly + x) + (lz + ly - y) * (lz + ly - y));
                }
                sb.append(res + "\n");
            }
        }
        System.out.print(sb);
    }
}