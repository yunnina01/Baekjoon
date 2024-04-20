import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> rockList;
    static int[] match;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        rockList = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            rockList.add(new ArrayList<>());

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rockList.get(x).add(y);
        }

        match = new int[n + 1];
        visit = new boolean[n + 1];
        int res = 0;
        for(int i = 1; i <= n; i++) {
            Arrays.fill(visit, false);
            if(DFS(i))
                res++;
        }
        System.out.println(res);
    }

    static boolean DFS(int x) {
        if(visit[x])
            return false;
        visit[x] = true;
        for(int now : rockList.get(x)) {
            if(match[now] == 0 || DFS(match[now])) {
                match[now] = x;
                return true;
            }
        }
        return false;
    }
}