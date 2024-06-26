import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 12000;
    static int[][] villages;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        villages = new int[N][];
        for(int i = 0; i < villages.length; i++)
            villages[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        int res = MAX;
        for(int i = 0; i < N; i++) {
            int first = MAX;
            int second = MAX;
            for(int j = 0; j < N; j++) {
                if(i == j)
                    continue;
                
                int dist = getDist(i, j);
                if(dist < second) {
                    if(dist < first) {
                        second = first;
                        first = dist;
                    } else
                        second = dist;
                }
            }
            res = Math.min(res, first + second);
        }
        System.out.println(res);
    }

    static int getDist(int a, int b) {
        return Math.abs(villages[a][0] - villages[b][0]) + Math.abs(villages[a][1] - villages[b][1]) + Math.abs(villages[a][2] - villages[b][2]);
    }
}