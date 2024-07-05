import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int res = 2;
        if(N < 3)
            res = N;
        else {
            Arrays.sort(A);
            for(int i = 0; i < N - 1; i++) {
                int x = A[i];
                int y = A[i + 1];
                for(int j = i + 2; j < N; j++) {
                    int z = A[j];
                    if(x + y <= z)
                        break;
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        System.out.println(res);
    }
}