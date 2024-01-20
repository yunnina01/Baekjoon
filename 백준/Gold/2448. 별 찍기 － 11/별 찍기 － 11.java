import java.io.*;

public class Main {
    static char[][] star;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        star = new char[N][2 * N];
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < i * 2 + 1; j++)
        		star[i][j] = '*';
        }

        makeBlank(N, 0, 0);
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N - 1 - i; j++)
        		sb.append(" ");
        	for(int j = 0; j < i * 2 + 1; j++)
        		sb.append(star[i][j]);
        	for(int j = 0; j < N - 1 - i; j++)
        		sb.append(" ");
        	sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    
    static void makeBlank(int k, int x, int y) {
    	if(k == 3) {
    		star[x + 1][y + 1] = ' ';
    		return;
    	}

    	for(int i = k / 2; i < k; i++) {
    		for(int j = i * 2 - k + 1; j < k; j++)
    			star[x + i][y + j] = ' ';
    	}
    	makeBlank(k / 2, x, y);
    	makeBlank(k / 2, x + k / 2, y);
    	makeBlank(k / 2, x + k / 2, y + k);
    }
}