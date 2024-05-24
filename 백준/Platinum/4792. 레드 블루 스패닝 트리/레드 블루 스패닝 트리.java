import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            if((n | m | k) == 0)
                break;
            
            List<List<Integer>> red = new ArrayList<>();
            List<List<Integer>> blue = new ArrayList<>();
            for(int i = 0; i <= n; i++) {
                red.add(new ArrayList<>());
                blue.add(new ArrayList<>());
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int f = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                if(c == 'B') {
                    blue.get(f).add(t);
                    blue.get(t).add(f);
                } else {
                    red.get(f).add(t);
                    red.get(t).add(f);
                }
            }
            
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++)
                parent[i] = i;
            int min = 0;
            int l = 0;
            for(int now = 1; now <= n; now++) {
                for(int next : red.get(now)) {
                    if(find(now) == find(next))
                        continue;
                    parent[find(now)] = parent[find(next)];
                    l++;
                }
            }

            span:for(int now = 1; now <= n; now++) {
                for(int next : blue.get(now)) {
                    if(find(now) == find(next))
                        continue;
                    parent[find(now)] = parent[find(next)];
                    l++;
                    min++;
                    if(l == n - 1)
                        break span;
                }
            }

            for(int i = 1; i <= n; i++)
                parent[i] = i;
            int max = n - 1;
            l = 0;
            for(int now = 1; now <= n; now++) {
                for(int next : blue.get(now)) {
                    if(find(now) == find(next))
                        continue;
                    parent[find(now)] = parent[find(next)];
                    l++;
                }
            }

            span:for(int now = 1; now <= n; now++) {
                for(int next : red.get(now)) {
                    if(find(now) == find(next))
                        continue;
                    parent[find(now)] = parent[find(next)];
                    l++;
                    max--;
                    if(l == n - 1)
                        break span;
                }
            }
            sb.append((min <= k && k <= max ? "1" : "0") + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        return parent[x] = parent[x] == x ? x : find(parent[x]);
    }
}