import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		while(N-- > 0) {
			char[] A = br.readLine().toCharArray();
			HashMap<Character, Integer> aMap = new HashMap<>();
			for(int i = 0; i < A.length; i++)
				aMap.put(A[i], i);

			char[] W = br.readLine().toCharArray();
			int[] w = new int[W.length];
			for(int i = 0; i < W.length; i++)
				w[i] = aMap.get(W[i]);
			
			char[] S = br.readLine().toCharArray();
			int[] s = new int[S.length];
			for(int i = 0; i < S.length; i++)
				s[i] = aMap.get(S[i]);
			
			int[] sp = KMP(w);
			List<Integer> list = new ArrayList<>();
			for(int shift = 0; shift < A.length; shift++) {
				int cnt = 0;
				int[] ss = new int[S.length];
				for(int j = 0; j < S.length; j++)
					ss[j] = (s[j] - shift + A.length) % A.length;

				for(int i = 0, j = 0; i < ss.length; i++) {
					while(j > 0 && ss[i] != w[j])
                        j = sp[j-1];
					if(ss[i] == w[j]) {
						if(++j == w.length) {
							j = sp[j-1];
							cnt++;
						}
					}
				}
		        if(cnt == 1)
                    list.add(shift);
			}
			if (list.isEmpty()) {
				sb.append("no solution\n");
			} else {
				if(list.size() == 1)
                    sb.append("unique:");
				else
                    sb.append("ambiguous:");
				for(int x : list)
                    sb.append(" " + x);
				sb.append("\n");
			}
			list.clear();
		}
		bw.write(sb.toString());
        bw.flush();
	}

	static int[] KMP(int[] arr) {
		int[] sp = new int[arr.length];
		for(int i = 1, j = 0; i < arr.length; i++) {
			while(j > 0 && arr[i] != arr[j])
                j = sp[j-1];
			if(arr[i] == arr[j])
                sp[i] = ++j;
		}
		return sp;
	}
}