import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int[] line = new int[N];
        int min = 0;
        for(int i = 0; i < N; i++) {
            line[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && line[j] + 1 > line[i])
                    line[i] = line[j] + 1;
            }
            min = Math.max(min, line[i]);
        }
        System.out.println(N - min);
    }
}