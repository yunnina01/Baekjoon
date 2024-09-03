import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        double[][] pnts = new double[N + 1][3];
        double x = 0, y = 0, z = 0;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x += pnts[i][0] = Double.parseDouble(st.nextToken());
            y += pnts[i][1] = Double.parseDouble(st.nextToken());
            z += pnts[i][2] = Double.parseDouble(st.nextToken());
        }
        x /= N;
        y /= N;
        z /= N;

        double alp = 0.1;
        double res = 0;
        for(int i = 0; i < 33333; i++) {
            int idx = 1;
            res = 0;
            for(int j = 1; j <= N; j++) {
                double dx = x - pnts[j][0];
                double dy = y - pnts[j][1];
                double dz = z - pnts[j][2];
                double tmp = Math.sqrt(dx * dx + dy * dy + dz * dz);
                if(tmp <= res)
                    continue;
                res = tmp;
                idx = j;
            }
            x += (pnts[idx][0] - x) * alp;
            y += (pnts[idx][1] - y) * alp;
            z += (pnts[idx][2] - z) * alp;
            alp *= 0.999;
        }

        if(Math.abs(res) < 1e-7)
            res = 0;
        System.out.println(String.format("%.2f", res));
    }
}