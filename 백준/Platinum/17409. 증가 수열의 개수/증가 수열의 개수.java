import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static int[][] tree, depth;
    static int[] A;

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for(int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        tree = new int[51][100001];
        depth = new int[51][100001];
        for(int i = 1; i <= N; i++) {
           for(int j = 1; j <= K; j++) {
              depth[j][i] = sum(j - 1, A[i] - 1);
              update(j, A[i], depth[j][i]);
           }
        }

        int res = 0;
        for(int i = 1; i <= N; i++) 
           res = (res + depth[K][i]) % MOD;
        System.out.println(res);
   }

    static void update(int x, int y, int val) {
      while(y <= 100000) {
         tree[x][y] = (tree[x][y] + val) % MOD;
         y += y & -y;
      }
   }

   static int sum(int x, int y) {
      int sum = 0;
      if(x == 0)
            return 1;

      while(y > 0) {
         sum = (sum + tree[x][y]) % MOD;
         y -= y & -y;
      }
      return sum;
   }
}