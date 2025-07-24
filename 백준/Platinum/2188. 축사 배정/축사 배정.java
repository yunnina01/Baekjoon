import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> barnList;
    static int[] link;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        barnList = new ArrayList<>();
        barnList.add(null);
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            barnList.add(new ArrayList<>(S));
            for(int j = 0; j < S; j++)
                barnList.get(i).add(Integer.parseInt(st.nextToken()));
        }

        link = new int[M + 1];
        int res = 0;
        for(int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            if(DFS(i))
                res++;
        }
        System.out.println(res);
    }

    static boolean DFS(int x) {
        visit[x] = true;
        for(int barn : barnList.get(x)) {
            if(visit[link[barn]])
                continue;
            if(link[barn] == 0 || DFS(link[barn])) {
                link[barn] = x;
                return true;
            }
        }
        return false;
    }
}