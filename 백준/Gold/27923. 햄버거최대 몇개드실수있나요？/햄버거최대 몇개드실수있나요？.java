import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		if(L > N)
            L = N;

        List<Integer> bur = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			bur.add(Integer.parseInt(st.nextToken()));
		Collections.sort(bur, Collections.reverseOrder());

		int[] sus = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int t = Integer.parseInt(st.nextToken()) - 1;
			sus[t]++;
		}

		int[] prefix = new int[N + 1];
		for(int i = 1 ; i <= N; i++)
			prefix[i] = prefix[i - 1] + sus[i - 1];

        List<Integer> cola = new ArrayList<>();
		for(int i = 1 ; i < L; i++)
			cola.add(prefix[i]);
		for(int i = L; i <= N; i++)
			cola.add(prefix[i] - prefix[i - L]);
		Collections.sort(cola, Collections.reverseOrder());

		long res = 0;
		for(int i = 0; i < N; i++) {
			if(cola.get(i) >= 30)
                continue;
			int div = (int)Math.pow(2, cola.get(i));
			res += bur.get(i) / div;
		}
		System.out.println(res);
	}
}