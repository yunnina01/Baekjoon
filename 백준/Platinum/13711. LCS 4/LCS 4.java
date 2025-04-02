import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer,Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            map.put(Integer.parseInt(st.nextToken()), i);

        List<Integer> lca = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        lca.add(-1);
        for(int i = 0; i < N; i++) {
            int A = map.get(Integer.parseInt(st.nextToken()));
            if(A > lca.get(lca.size() - 1))
                lca.add(A);
            else {
                int left = 0;
                int right = lca.size() - 1;
                while(left + 1 < right) {
                    int mid = (left + right) >> 1;
                    if(lca.get(mid) >= A)
                        right = mid;
                    else
                        left = mid;
                }
                lca.set(right, A);
            }
        }
        System.out.println(lca.size() - 1);
    }
}