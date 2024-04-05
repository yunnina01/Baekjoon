import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> workList;
    static int[] workIdx;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        workList = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            workList.add(new ArrayList<>());
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0; j < count; j++) {
                int work = Integer.parseInt(st.nextToken());
                workList.get(i).add(work);
            }
        }

        workIdx = new int[M + 1];
        visit = new boolean[M + 1];
        int res = 0;
        for(int i = 1; i <= N; i++) {
            Arrays.fill(visit, false);
            if(DFS(i))
                res++;
        }
        System.out.println(res);
    }

    static boolean DFS(int x) {
        for(int work : workList.get(x)) {
            if(visit[work])
                continue;
            visit[work] = true;
            if(workIdx[work] == 0 || DFS(workIdx[work])) {
                workIdx[work] = x;
                return true;
            }
        }
        return false;
    }
}