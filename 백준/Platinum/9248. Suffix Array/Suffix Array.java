import java.io.*;
import java.util.*;

public class Main {
	static int len;
	static int[] rank, nextRank;
	static String S;	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		S = br.readLine();

		len = S.length();
		Integer[] sa = new Integer[len];
		rank = new int[len];
		nextRank = new int[len];
		for(int i = 0; i < len; i++) {
			sa[i] = i;
			rank[i] = S.charAt(i) - 'a';
		}
		Arrays.sort(sa, cmp);

		int[] tRank = new int[len];
		for(int next = 1; next <= len; next *= 2) {
			tRank[sa[0]] = 0;
			for(int i = 1; i < len; i++) {
				tRank[sa[i]] = tRank[sa[i - 1]];
				if(rank[sa[i]] != rank[sa[i - 1]] || nextRank[sa[i]] != nextRank[sa[i - 1]])
					tRank[sa[i]]++;
			}
			rank = Arrays.copyOf(tRank, len);
			for(int i = 0; i < len; i++) {
				if(sa[i] + next >= len)
					nextRank[sa[i]] = -1;
				else
					nextRank[sa[i]] = rank[sa[i] + next];
			}
			Arrays.sort(sa, cmp);
		}

		int[] index = new int[len];
		for(int i = 0; i < len; i++)
			index[sa[i]] = i;

		int[] lcp = new int[len];
		int temp = 0;
		for(int i = 0; i < len; i++) {
			if(index[i] != 0) {
				temp = commonPrefix(i, sa[index[i] - 1], temp);
				lcp[index[i]] = temp;
			}
		}

		for(int i = 0; i < len; i++)
			sb.append(sa[i] + 1 + " ");
		sb.append("\nx ");
		for(int i = 1; i < len; i++)
			sb.append(lcp[i] + " ");
		bw.write(sb + "\n");
        br.close();
		bw.flush();
		bw.close();
	}

	static Comparator<Integer> cmp = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			if(rank[o1] == rank[o2])
				return nextRank[o1] - nextRank[o2];
			return rank[o1] - rank[o2];
		}
	};
	
	static int commonPrefix(int idx1, int idx2, int prev) {
		int size = len - Math.max(idx1, idx2);
		int lcp = ((prev == 0) ? 0 : (prev - 1));
		for(int i = lcp; i < size; i++) {
			if(S.charAt(idx1 + i) == S.charAt(idx2 + i))
				lcp++;
			else
				break;
		}
		return lcp;
	}
}