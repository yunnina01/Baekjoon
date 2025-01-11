import java.io.*;

public class Main {
    static final int LIMIT = 44720;
    static int[] sumOfSeq = new int[LIMIT + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= LIMIT; i++)
            sumOfSeq[i] = sumOfSeq[i - 1] + i;
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int res = getres(N);
            if(res == -1) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }

            sb.append(N + " = ");
            int base = (N - sumOfSeq[res]) / res;
            for(int i = 1; i < res; i++)
                sb.append((base + i) + " + ");
            sb.append((base + res) + "\n");
        }
        System.out.print(sb);
    }

    static int getres(int n) {
        for(int i = 2; i <= LIMIT; i++) {
            int tmp = n - sumOfSeq[i];
            if(tmp < 0)
                return -1;
            if(tmp % i == 0)
                return i;
        }
        return -1;
    }
}