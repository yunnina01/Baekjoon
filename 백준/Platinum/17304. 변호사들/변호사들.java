import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> defenseList = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            defenseList.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            defenseList.get(A).add(B);
        }

        boolean[] defensed = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 0, size = defenseList.get(i).size(); j < size;) {
                int v = defenseList.get(i).get(j);
                if(!defenseList.get(v).contains(i)) {
                    defensed[v] = true;
                    defenseList.get(i).remove(Integer.valueOf(v));
                    size--;
                } else
                    j++;
            }
        }

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++)
            parent[i] = i;

        for(int from = 1; from <= N; from++) {
            for(int i = 0; i < defenseList.get(from).size(); i++) {
                int to = defenseList.get(from).get(i);
                defenseList.get(to).remove(Integer.valueOf(from));

                if(find(to) == find(from))
                    defensed[find(to)] = true;
                else {
                    if(defensed[find(to)])
                        union(to, from);
                    else
                        union(from, to);
                }
            }
        }

        String res = "YES";
        for(int i = 1; i <= N ; i++) {
            if(defensed[i] || defensed[find(i)])
                continue;
            res = "NO";
        }
        System.out.println(res);
    }

    static int find(int x) {
        return parent[x] = parent[x] == x ? x : find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b)
            parent[b] = a;
    }
}