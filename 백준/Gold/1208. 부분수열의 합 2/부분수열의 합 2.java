import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();
        divide(0, N / 2, 0, leftList);
        divide(N / 2, N, 0, rightList);
        Collections.sort(leftList);
        Collections.sort(rightList);

        int left = 0, right = rightList.size() - 1;
        long res = 0;
        while(left < leftList.size() && right >= 0) {
            long sum = leftList.get(left) + rightList.get(right);
            if(sum == S) {
                long l = leftList.get(left);
                long tmpL = 0;
                while(left < leftList.size() && leftList.get(left) == l) {
                    left++;
                    tmpL++;
                }

                long r = rightList.get(right);
                long tmpR = 0;
                while(right >= 0 && rightList.get(right) == r) {
                    right--;
                    tmpR++;
                }
                res += tmpL * tmpR;
            } else if(sum < S)
                left++;
            else
                right--;
        }
        if(S == 0)
            res--;
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void divide(int start, int end, long sum, List<Long> list) {
        if(start == end) {
            list.add(sum);
            return;
        }
        divide(start + 1, end, sum + nums[start], list);
        divide(start + 1, end, sum, list);
    }
}