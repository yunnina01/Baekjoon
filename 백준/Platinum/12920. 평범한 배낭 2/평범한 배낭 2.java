import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<int[]> things = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
        	int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

        	for(int j = K; j > 0; j >>= 1) {
        		int pack = j - (j >> 1);
        		things.offer(new int[] {V * pack, C * pack});
        	}
        }

        int[][] best = new int[things.size()][M + 1];
        for(int i = 0; i < things.size(); i++) {
        	int[] thing = things.get(i);
        	for(int j = 0; j < M + 1; j++) {
        		if(j >= thing[0])
        			best[i][j] = Math.max(i == 0 ? thing[1] : thing[1] + best[i - 1][j - thing[0]], i == 0 ? 0 : best[i - 1][j]);
        		else if(i != 0)
        			best[i][j] = best[i - 1][j];
        	}
        }
        System.out.println(best[things.size() - 1][M]);
    }
}