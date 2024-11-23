import java.io.*;
import java.util.*;

public class Main {
    static final int P = 31;
    static final int SIZE = 26;
    static long[] pows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        if(str1.length() > str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
        }

        pows = new long[SIZE];
        pows[0] = 1;
        for(int i = 1; i < SIZE; i++)
            pows[i] = P * pows[i - 1];

        int maxGap = 0;
        for(int i = 1; i < str1.length() + 1; i++) {
            Set<Long> setA = new HashSet<>();
            Set<Long> setB = new HashSet<>();

            long hashA = 0, hashB = 0;
            for(int j = 0; j < str2.length() - i + 1; j++) {
                if(j == 0) {
                    hashA = hash(str1, i);
                    hashB = hash(str2, i);
                } else {
                    if(j + i - 1 < str1.length()) {
                        int aIdxLeft = str1.charAt(j - 1) - 'a';
                        int aIdxRight = str1.charAt(j + i - 1) - 'a';
                        hashA = j + i - 1 < str1.length() ? hashA - pows[aIdxLeft] + pows[aIdxRight] : hashA;
                    }

                    int bIdxLeft = str2.charAt(j - 1) - 'a';
                    int bIdxRight = str2.charAt(j + i - 1) - 'a';
                    hashB = hashB - pows[bIdxLeft] + pows[bIdxRight];
                }
                setA.add(hashA);
                setB.add(hashB);
            }

            int sum = setA.size() + setB.size();
            setA.addAll(setB);
            
            boolean flag = sum != setA.size();
            if(flag)
                maxGap = i;
        }
        System.out.println(maxGap);
    }

    static long hash(String str, int end) {
        int[] table = new int[SIZE];
        for(int i = 0; i < end; i++)
            table[str.charAt(i) - 'a']++;

        long hash = 0;
        for(int i = 0; i < SIZE; i++)
            hash = hash + table[i] * pows[i];
        return hash;
    }
}