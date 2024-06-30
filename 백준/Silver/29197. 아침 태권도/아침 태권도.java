import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		Set<String> zero = new HashSet<>();
		Set<Double> plus = new HashSet<>();
		Set<Double> minus = new HashSet<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double X = Double.parseDouble(st.nextToken());
            double Y = Double.parseDouble(st.nextToken());
            
            if(X == 0 && Y != 0)
                zero.add((Y > 0) ? "x" : "-x");
            else if(Y == 0 && X != 0)
                zero.add((X > 0) ? "y" : "-y");
            else {
                if(X > 0)
                    plus.add(X / Y);
                else if(X < 0)
                    minus.add(X / Y);
            }
        }
        System.out.print(zero.size() + plus.size() + minus.size());
    }
}