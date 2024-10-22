import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<String> nums = new ArrayList<>();
        for(int i = 0; i < K; i++)
            nums.add(br.readLine());

        Collections.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() == o2.length() ? (o2 + o1).compareTo(o1 + o2) : o2.length() - o1.length();
            }
        });

        for(int i = K; i < N; i++)
            nums.add(nums.get(0));
        Collections.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -(o1 + o2).compareTo(o2 + o1);
            }
        });

        for(String s : nums)
            sb.append(s);
        System.out.println(sb);
    }
}