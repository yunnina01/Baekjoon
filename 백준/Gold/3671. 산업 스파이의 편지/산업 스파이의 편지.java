import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> numList;
    static int[] curNum;
    static boolean[] ch, visit;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        ch = new boolean[10_000_001];
        ch[0] = ch[1] = true;
        for(int i = 2; i <= Math.sqrt(10000000); i++) {
            for(int j = i * i; j <= 10000000; j += i)
                ch[j] = true;
        }

        int c = Integer.parseInt(br.readLine());

        while(c -- > 0) {
            String num = br.readLine();
            if(Integer.parseInt(num) == 0) {
                sb.append("0\n");
                continue;
            }

            numList = new ArrayList<>();
            len = num.length();
            for(int i = 0; i < len; i++)
                numList.add((int)num.charAt(i) - '0');
            Collections.sort(numList);

            curNum = new int[len];
            visit = new boolean[len];
            sb.append(DFS(0) + "\n");
        }
        System.out.print(sb);
    }

    static int DFS(int L) {
        int res = 0;
        if(!ch[getNumber(L)])
            res ++;
        if(L == len)
            return res;

        int last = -1;
        for(int i = 0; i < numList.size(); i++) {
            if(visit[i])
                continue;
            int tmp = numList.get(i);
            if(L == 0 && tmp == 0)
                continue;
            if(last == tmp)
                continue;

            visit[i] = true;
            curNum[L] = tmp;
            last = tmp;
            res += DFS(L + 1);
            visit[i] = false;
        }
        return res;
    }

    static int getNumber(int L) {
        int ret = 0;
        for(int i = 0; i < L; i++) {
            ret *= 10;
            ret += curNum[i];
        }
        return ret;
    }
}