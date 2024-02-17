import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;
    static int[] pows;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] scoville = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        pows = new int[N + 1];
        long res = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++)
                res += ((long)(scoville[j] - scoville[i])) * pow(j - i - 1) % MOD;
        }
        bw.write(res % MOD + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static long pow(int k){
        if(k < 2)
            return k+1;
        if(pows[k] != 0)
            return pows[k];
        long prev = pow(k / 2);
        return pows[k] = (int)(k % 2 == 0 ? (prev * prev) % MOD : (prev * prev * 2) % MOD);
    }
}