import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10007;
    static final int CIPHER = 29;
    static List<Integer>[] hashTable = new ArrayList[MOD];
    static int[] pexp;
    static String str;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        pexp = new int[200005];
        pexp[0] = 1;
        for(int i = 1; i <= 200000; i++)
            pexp[i] = (pexp[i - 1] * CIPHER) % MOD;

        L = Integer.parseInt(br.readLine());
        str = br.readLine();
        
        int left = 0;
        int right = L;
        while(left < right) {
            int mid = (left + right + 1) >> 1;
            if(find(mid))
                left = mid;
            else
                right = mid - 1;
        }
        bw.write(left + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean find(int pos) {
    	for(int i = 0; i < MOD; i++)
            hashTable[i] = new ArrayList<>();

        int hash = 0;
        for(int i = 0; i < pos; i++)
            hash = (hash * CIPHER + str.charAt(i) - 'a') % MOD;

        hashTable[hash].add(0);
        for(int i = pos; i < L; i++) {
            hash = ((hash - (str.charAt(i - pos) - 'a') * pexp[pos - 1]) % MOD + MOD) % MOD;
            hash = (hash * CIPHER + str.charAt(i) - 'a') % MOD;
            if(!hashTable[hash].isEmpty()) {
                for(int key : hashTable[hash]) {
                    if(isMatach(key, i - pos + 1, pos))
                        return true;
                }
            }
            hashTable[hash].add(i - pos + 1);
        }
        return false;
    }

    static boolean isMatach(int key, int s, int pos) {
        for(int i = 0; i < pos; i++) {
            if (str.charAt(key + i) != str.charAt(s + i))
                return false;
        }
        return true;
    }
}