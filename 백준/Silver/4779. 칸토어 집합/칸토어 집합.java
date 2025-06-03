import java.io.*;

public class Main {
    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str;

        while((str = br.readLine()) != null) {
            N = Integer.parseInt(str);
            sb = new StringBuilder();
            int len = (int)Math.pow(3, N);
            for(int i = 0; i < len; i++)
                sb.append("-");
            
            cantorSet(0, len);
            bw.write(sb + "\n");
        }
        bw.flush();
    }

    static void cantorSet(int start, int len) {
        if(len == 1)
            return;
        for(int i = start + len / 3; i < start + len / 3 * 2; i++)
            sb.setCharAt(i, ' ');
        cantorSet(start, len / 3);
        cantorSet(start + len / 3 * 2, len / 3);
    }
}