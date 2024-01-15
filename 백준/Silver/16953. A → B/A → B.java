import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
        boolean flag = false;
		int res = 1;
		while(B != 0) {
			if(B % 2 == 0)
                B /= 2;
			else if(B % 10 == 1)
                B /= 10;
			else
                break;
			res++;
			
			if(A == B) {
				flag = true;
				break;
			}
		}
        bw.write((flag ? res : -1) + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
}