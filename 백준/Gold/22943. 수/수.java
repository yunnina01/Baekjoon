import java.io.*;
import java.util.*;

public class Main {
    static final int LIMIT = 98765;
    static final int DIGIT = 10;
    static List<Integer> pnList, numList;
    static Set<Integer> mulSet, sumSet;
    static boolean[] visit;
    static int K, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numList = new ArrayList<>();
        visit = new boolean[DIGIT];
        makeNum(0, "");

        pnList = new ArrayList<>();
        eratos();

        mulSet = new HashSet<>();
        sumSet = new HashSet<>();
        calc();

        int res = 0;
        for(int num : numList) {
            if(!sumSet.contains(num))
                continue;
            while(num % M == 0)
                num /= M;
            if(mulSet.contains(num))
                res++;
        }
        System.out.print(res);
    }

    static void makeNum(int depth, String num) {
        if(depth == K)
            numList.add(Integer.parseInt(num));
        else {
            for(int i = 0; i < DIGIT; i++) {
                if(depth == 0 && i == 0)
                    continue;
                if(visit[i])
                    continue;
                visit[i] = true;
                makeNum(depth + 1, num + i);
                visit[i] = false;
            }
        }
    }

    static void eratos() {
        boolean[] isPrime = new boolean[LIMIT + 1];
        for(int i = 2; i <= LIMIT; i++) {
            if(isPrime[i])
                continue;
            pnList.add(i);
            for(int j = i * 2; j <= LIMIT; j += i)
                isPrime[j] = true;
        }
    }

    static void calc() {
        for(int i = 0; i < pnList.size(); i++) {
            for(int j = 0; j < pnList.size(); j++) {
                int pn1 = pnList.get(i);
                int pn2 = pnList.get(j);
                long mul = (long)pn1 * pn2;

                if(mul <= LIMIT)
                    mulSet.add((int)mul);
                if(pn1 != pn2) {
                    int sum = pn1 + pn2;
                    if(sum <= LIMIT)
                        sumSet.add(sum);
                }
            }
        }
    }
}