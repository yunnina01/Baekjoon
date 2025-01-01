import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        String[][] strs = new String[N][100];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            int idx = 0;
            for(int j = 0; j < line.length(); j++) {
                if('0' <= line.charAt(j) && line.charAt(j) <= '9') {
                    int end = j + 1;
                    while(end < line.length() && '0' <= line.charAt(end) && line.charAt(end) <= '9')
                        end++;

                    strs[i][idx++] = line.substring(j, end);
                    j = end - 1;
                } else
                    strs[i][idx++] = String.valueOf(line.charAt(j));
            }
        }

        int[] comp = new int['z' - '0' + 1];
        for(int i = '0' - '0'; i <= '9' - '0'; i++)
            comp[i] = 0;
        for(int i = 'A' - '0', j = 1; i <= 'Z' - '0'; i++, j += 2)
            comp[i] = j;
        for(int i = 'a' - '0', j = 2; i <= 'z' - '0'; i++, j += 2)
            comp[i] = j;

        Arrays.sort(strs, (o1, o2) -> {
            int idx;
            for(idx = 0; idx < 100 && o1[idx] != null && o2[idx] != null; idx++) {
                if('0' <= o1[idx].charAt(0) && o1[idx].charAt(0) <= '9' && '0' <= o2[idx].charAt(0) && o2[idx].charAt(0) <= '9') {
                    int len1 = o1[idx].length();
                    int len2 = o2[idx].length();
                    int zero1 = 0, zero2 = 0;
                    while(zero1 < len1 && o1[idx].charAt(zero1) == '0')
                        zero1++;
                    while(zero2 < len2 && o2[idx].charAt(zero2) == '0')
                        zero2++;

                    if(len1 - zero1 == len2 - zero2) {
                        for(int i = 0; i < len1 - zero1; i++) {
                            if(o1[idx].charAt(zero1 + i) == o2[idx].charAt(zero2 + i))
                                continue;
                            return o1[idx].charAt(zero1 + i) - o2[idx].charAt(zero2 + i);
                        }
                        if(len1 == len2)
                            continue;
                        else
                            return len1 - len2;
                    }
                    return (len1 - zero1) - (len2 - zero2);
                }
                if(comp[o1[idx].charAt(0) - '0'] == comp[o2[idx].charAt(0) - '0'])
                    continue;
                return comp[o1[idx].charAt(0) - '0'] - comp[o2[idx].charAt(0) - '0'];
            }
            if(idx >= 100)
                return 0;
            else if(o1[idx] == null && o2[idx] == null)
                return 0;
            else if(o1[idx] == null)
                return -1;
            else if(o2[idx] == null)
                return 1;
            return 0;
        });

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 100 && strs[i][j] != null; j++)
                sb.append(strs[i][j]);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}