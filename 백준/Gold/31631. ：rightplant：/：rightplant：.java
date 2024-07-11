import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] H = new int[N];
        int start = 0;
        int end = N - 1;
        int leftSum = 0;
        int rightSum = 0;

        for(int i = 0; i < N; i++) {
            if(leftSum < rightSum) {
                H[start++] = N - i;
                leftSum += N - i;
            } else {
                H[end--] = N - i;
                rightSum += N - i;
            }
        }

        for(int h : H)
            sb.append(h + " ");
        System.out.println(sb);
    }
}