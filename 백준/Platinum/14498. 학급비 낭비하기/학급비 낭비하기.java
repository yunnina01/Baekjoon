import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_SIZE = 550;
    static List<List<Integer>> edges;
    static int[] candy;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] data = new int[k][3];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++)
                data[i][j] = Integer.parseInt(st.nextToken());
        }

        edges = new ArrayList<>();
        for(int i = 0; i < MAX_SIZE; i++)
            edges.add(new ArrayList<>());

        for(int i = 0; i < k; i++) {
            for(int j = i + 1; j < k; j++) {
                if((data[i][0] == data[j][0] || data[i][1] == data[j][1]) && data[i][2] != data[j][2]) {
                    if(data[i][2] == 0)
                        edges.get(i).add(j);
                    else
                        edges.get(j).add(i);
                }
            }
        }

        candy = new int[MAX_SIZE];
        int res = 0;
        Arrays.fill(candy, -1);
        for(int i = 0; i < k; i++) {
            visit = new boolean[MAX_SIZE];
            if(DFS(i))
                res++;
        }
        System.out.println(res);
    }

    static boolean DFS(int x) {
        for(int now : edges.get(x)) {
            if(visit[now])
                continue;
            visit[now] = true;
            if(candy[now] == -1 || DFS(candy[now])) {
                candy[now] = x;
                return true;
            }
        }
        return false;
    }
}