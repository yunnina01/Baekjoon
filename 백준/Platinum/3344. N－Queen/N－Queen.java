import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        switch(N % 6) {
            case 2:
                for(int i = 2; i <= N; i += 2)
                    sb.append(i + "\n");
                sb.append(3 + "\n");
                sb.append(1 + "\n");
                for(int i = 7; i <= N; i += 2)
                    sb.append(i + "\n");
                sb.append(5 + "\n");
                break;
            case 3:
                for(int i = 4; i <= N; i += 2)
                    sb.append(i + "\n");
                sb.append(2 + "\n");
                for(int i = 5; i <= N; i += 2)
                    sb.append(i + "\n");
                sb.append(1 + "\n");
                sb.append(3 + "\n");
                break;
            default:
                for(int i = 2; i <= N; i += 2)
                    sb.append(i + "\n");
                for(int i = 1; i <= N; i += 2)
                    sb.append(i + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}