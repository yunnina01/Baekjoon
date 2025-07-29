import java.io.*;
import java.util.*;

public class Main {
   static List<List<Integer>> edges, sccEdges;
   static Stack<Integer> stack;
   static int[] sn, dfsn, dp, num, id;
   static boolean[] visit, canReach;
   static int scc, cnt;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int S = Integer.parseInt(st.nextToken()) - 1;
      int T = Integer.parseInt(st.nextToken()) - 1;

      edges = new ArrayList<>();
      for(int i = 0; i < N; i++)
            edges.add(new ArrayList<>());

      for(int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine());
         int start = Integer.parseInt(st.nextToken()) - 1;
         int end = Integer.parseInt(st.nextToken()) - 1;
         edges.get(start).add(end);
      }
        
        stack = new Stack<>();
      sn = new int[N];
      dfsn = new int[N];
      visit = new boolean[N];
      scc = 0;
      cnt = 0;
      for(int i = 0; i < N; i++) {
         if(dfsn[i] == 0)
                getSCC(i);
      }

      sccEdges = new ArrayList<>();
      num = new int[scc];
      id = new int[scc];
      for(int i = 0; i < scc; i++)
            sccEdges.add(new ArrayList<>());

      for(int i = 0; i < N; i++) {
         for(int n : edges.get(i)) {
            if(sn[i] != sn[n]) { 
               id[sn[n]]++;
               sccEdges.get(sn[i]).add(sn[n]);
            }
         }
         num[sn[i]]++;
      }
        
      dp = new int[scc];
      canReach = new boolean[scc];
      dp[sn[S]] = num[sn[S]];
      canReach[sn[S]] = true;
      topologySort(S);

      System.out.println(dp[sn[T]]);
   }
   
   static int getSCC(int i) {
      stack.push(i);
      dfsn[i] = ++cnt;
      
      int ret = dfsn[i];
      for(int next : edges.get(i)) {
         if(dfsn[next] == 0)
                ret = Math.min(ret, getSCC(next));
         else if(!visit[next])
                ret = Math.min(ret, dfsn[next]);
      }
      
      if(ret == dfsn[i]) {
         while(true) {
            int top = stack.pop();
            visit[top] = true;
            sn[top] = scc;
            if(top == i)
                    break;
         }
         scc++;
      }
      return ret;
   }
   
   static void topologySort(int S) {
      Queue<Integer> queue = new LinkedList<>();
      for(int i = 0; i < scc; i++) {
         if(id[i] == 0)
                queue.offer(i);
      }

      while(!queue.isEmpty()) {
         int now = queue.poll();
         for(int next : sccEdges.get(now)) {
            if(canReach[now]) {
               dp[next] = Math.max(dp[next], dp[now] + num[next]);
               canReach[next] = true;
            }
            if(--id[next] == 0)
                    queue.offer(next);
         }
      }
   }
}