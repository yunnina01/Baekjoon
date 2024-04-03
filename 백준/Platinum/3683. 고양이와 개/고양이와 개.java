import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> workList;
    static boolean[] visit;
    static int[] link;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            List<Integer> clink = new ArrayList<>();
            List<Integer> dlink = new ArrayList<>();
            clink.add(-1);
            dlink.add(-2);

            for(int i = 0; i < v; i++) {
                st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                int fnum = Integer.parseInt(first.substring(1));
                int lnum = Integer.parseInt(st.nextToken().substring(1));
                if(first.charAt(0) == 'C')
                    clink.add(fnum * 1000 + lnum);
                else
                    dlink.add(lnum * 1000 + fnum);
            }

            workList = new ArrayList<>();
            workList.add(null);
            for(int i = 1; i < clink.size(); i++) {
                List<Integer> tmp = new ArrayList<>();
                int cnum = clink.get(i);
                for(int j = 1; j < dlink.size(); j++) {
                    int dnum = dlink.get(j);
                    if(cnum / 1000 == dnum / 1000 || cnum % 1000 == dnum % 1000)
                        tmp.add(j);
                }
                workList.add(tmp);
            }

            int res = 0;
            link = new int[dlink.size() + 1];
            for(int i = 1; i < workList.size(); i++) {
                visit = new boolean[workList.size() + 1];
                visit[i] = true;
                if(DFS(i))
                    res++;
            }
            sb.append(v - res + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean DFS(int x) {
        for(int work : workList.get(x)) {
            if(link[work] == 0) {
                link[work] = x;
                return true;
            } else if(!visit[link[work]]) {
                visit[link[work]] = true;
                if(DFS(link[work])) {
                    link[work] = x;
                    return true;
                }
            }
        }
        return false;
    }
}