import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> pairList;
    static int[] aMatch, bMatch;
    static boolean[] visit, primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        boolean isFirstEven = nums[0] % 2 == 0;
        aList.add(nums[0]);
        for(int i = 1; i < N; i++) {
            if(isFirstEven) {
                if(nums[i] % 2 == 0)
                    aList.add(nums[i]);
                else
                    bList.add(nums[i]);
            } else {
                if(nums[i] % 2 != 0)
                    aList.add(nums[i]);
                else
                    bList.add(nums[i]);
            }
        }

        if(aList.size() != bList.size())
            bw.write("-1");
        else {
            primes = new boolean[2001];
            eratos();

            pairList = new ArrayList<>();
            for(int now : aList) {
                List<Integer> pair = new ArrayList<>();
                for(int i = 0; i < bList.size(); i++) {
                    int plus = bList.get(i);
                    if(!primes[now + plus])
                        pair.add(i);
                }
                pairList.add(pair);
            }

            List<Integer> res = new ArrayList<>();
            for(int idx : pairList.get(0)) {
                aMatch = new int[aList.size()];
                bMatch = new int[bList.size()];
                Arrays.fill(aMatch, -1);
                Arrays.fill(bMatch, -1);

                aMatch[0] = idx;
                bMatch[idx] = 0;

                int cnt = 1;
                visit = new boolean[aList.size()];
                for(int i = 1; i < aList.size(); i++) {
                    Arrays.fill(visit, false);
                    if(DFS(i))
                        cnt++;
                }

                if(cnt == bList.size())
                    res.add(bList.get(idx));
            }

            if(res.size() > 0) {
                res.sort(Comparator.naturalOrder());
                for(int n : res)
                    bw.write(n + " ");
            } else
                bw.write("-1");
        }
        bw.newLine();
        br.close();
        bw.flush();
        bw.close();
    }

    static void eratos() {
        for(int i = 2; i <= 2000 / i; i++) {
            if(primes[i])
                continue;
            for(int j = i * i; j <= 2000; j += i)
                primes[j] = true;
        }
    }

    static boolean DFS(int x) {
        if(x == 0 || visit[x])
            return false;
        visit[x] = true;
        for(int idx : pairList.get(x)) {
            if(bMatch[idx] == -1 || DFS(bMatch[idx])) {
                aMatch[x] = idx;
                bMatch[idx] = x;
                return true;
            }
        }
        return false;
    }
}