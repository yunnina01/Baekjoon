import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static Result[] result;
	static Trie root;
	static int L, C, W;
	
	static class Trie {
		Trie[] next;
		Trie fail;
		Output output;
		
		Trie() {
			next = new Trie[26];
		}
		
		void setOutput(Output output) {
			if(output == null)
				return;

			if(this.output == null)
				this.output = output;
			else
				this.output.merge(output);
		}
		
		void insert(String str, int num, boolean reverse) {
			Trie now;
            int len = str.length(), i, idx;
			for(now = this, i = 0; i < len; i++, now = now.next[idx]) {
				idx = (reverse ? str.charAt(len - i - 1) : str.charAt(i)) - 'A';
				if(now.next[idx] == null)
					now.next[idx] = new Trie();
			}

			if(reverse)
				now.setOutput(new Output(num));
			else {
				now.setOutput(new Output(num, len));
				insert(str, num, true);
			}
		}
		
		Trie go(int idx) {
			if(next[idx] == null) {
				if(this == root)
					return this;
				return fail.go(idx);
			}
			return next[idx];
		}
		
		void buildFail() {
			ArrayDeque<Trie> dq = new ArrayDeque<>();
			for(Trie first : this.next) {
				if(first == null)
					continue;
				first.fail = this;
				dq.offerLast(first);
			}

			while(!dq.isEmpty()) {
				Trie now = dq.pollFirst();
				for(int i = 0; i < 26; i++) {
                    Trie next=  now.next[i];
					if(next == null)
						continue;
					next.fail = now.fail.go(i);
					next.setOutput(next.fail.output);
					dq.offerLast(next);
				}
			}
		}

		void searchA(int y) {
			Trie now = this;
			for(int x = L - 1; x >= 0; x--) {
				now = now.go(map[x][y]);
				for(Output output = now.output; output != null; output = output.next)
					output.found(x, y, 'A');
			}
		}
		
		void searchB(int x, int y) {
			Trie now = this;
			for(; x >= 0 && y < C; x--, y++) {
				now = now.go(map[x][y]);
				for(Output output = now.output; output != null; output = output.next)
					output.found(x, y, 'B');
			}
		}
		
		void searchC(int x) {
			Trie now = this;
			for(int y = 0; y < C; y++) {
				now = now.go(map[x][y]);
				for(Output output = now.output; output != null; output = output.next)
					output.found(x, y, 'C');

			}
		}

		void searchD(int x, int y) {
			Trie now = this;
			for(; x < L && y < C; x++, y++) {
				now = now.go(map[x][y]);
				for(Output output = now.output; output != null; output = output.next)
					output.found(x, y, 'D');
			}
		}
	}
	
	static class Output {
		int idx, len;
		Output next, tail;
		
		Output(int idx) {
			this.idx = idx;
			this.tail = this;
		}
		
		Output(int idx, int len) {
			this(idx);
			this.len = len;
		}
		
		void merge(Output output) {
			this.tail.next = output;
			this.tail = output.tail;
		}
		
		void found(int x, int y, char dir) {
			if(len == 0)
				dir += 4;
			else {
				switch (dir) {
                    case 'A':
                        x += len - 1;
                        break;
                    case 'B':
                        x += len - 1;
                        y -= len - 1;
                        break;
                    case 'C':
                        y -= len - 1;
                        break;
                    case 'D':
                        x -= len - 1;
                        y -= len - 1;
                }
			}
			if(result[idx] == null)
				result[idx] = new Result(x, y, dir);
			else
				result[idx].setResult(x, y, dir);
		}
	}
	
	static class Result {
		int x, y;
		char dir;
		
		Result(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		void setResult(int x, int y, char dir) {
			if(x < this.x || (x == this.x && y < this.y) || (x == this.x && y == this.y && dir < this.dir)) {
				this.x = x;
				this.y = y;
				this.dir = dir;
			}
		}

		@Override
		public String toString() {
			return x + " " + y + " " + dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new int[L][];
		for(int i = 0; i < L; i++)
			map[i] = parse(br.readLine());

		root = new Trie();
		for(int i = 1; i <= W; i++)
			root.insert(br.readLine(), i, false);

		root.buildFail();
		search();

		for(int i = 1; i <= W; i++)
			sb.append(result[i] + "\n");
		bw.write(sb.toString());
        bw.flush();
	}
	
	static int[] parse(String str) {
		int[] result = new int[C];
		for(int i = 0; i < C; i++)
			result[i] = str.charAt(i) - 'A';
		return result;
	}
	
	static void search() {
		result = new Result[W + 1];
		root.searchA(0);
		root.searchB(0, 0);
		root.searchC(0);
		root.searchD(0, 0);
		for(int i = 1; i < C; i++) {
			root.searchA(i);
			root.searchB(L - 1, i);
			root.searchD(0, i);
		}
		for(int i = 1; i < L; i++) {
			root.searchB(i, 0);
			root.searchC(i);
			root.searchD(i, 0);
		}
	}
}