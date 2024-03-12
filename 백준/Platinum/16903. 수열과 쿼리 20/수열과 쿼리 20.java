import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Node root = new Node();
		int bit = 1 << 29;
        add(root, 0, bit);

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if(q == 1) 
				add(root, x, bit);
			else if(q == 2)
				delete(root, x, bit);
			else
				sb.append(sum(root, x, bit, 0) + "\n");
		}
		bw.write(sb.toString());
        br.close();
		bw.flush();
		bw.close();
	}
    
    static void add(Node node, int num, int bit) {
		node.cnt++;
		if(bit == 0)
			return;
		if((num & bit) == 0) {
			if(node.zero == null)
				node.zero = new Node();
			add(node.zero, num & (bit - 1), bit >> 1);
		} else {
			if(node.one == null)
				node.one = new Node();
			add(node.one, num & (bit - 1), bit >> 1);
		}
	}

	static void delete(Node node, int num, int bit) {
		node.cnt--;
		if(bit == 0)
			return;
		if((num & bit) == 0) {
			if(node.zero.cnt == 1) {
				node.zero = null;
				return;
			}
			delete(node.zero, num & (bit - 1), bit >> 1);
		} else {
			if(node.one.cnt == 1) {
				node.one = null;
				return;
			}
			delete(node.one, num & (bit - 1), bit >> 1);
		}
	}
	
	static int sum(Node node, int num, int bit, int res) {
		if(bit == 0)
			return res;
		if((num & bit) == 0) {
			if(node.one != null && node.one.cnt > 0) {
				res = (res << 1) + 1;
				return sum(node.one, num & (bit - 1), bit >> 1, res);
			} else {
				res = (res << 1) + 0;
				return sum(node.zero, num & (bit - 1), bit >> 1, res);
			}
		} else {
			if(node.zero != null && node.zero.cnt > 0) {
				res = (res << 1) + 1;
				return sum(node.zero, num & (bit - 1), bit >> 1, res);
			} else {
				res = (res << 1) + 0;
				return sum(node.one, num & (bit - 1), bit >> 1, res);
			}
		}
	}
}

class Node {
    Node zero, one;
    int cnt;
    
    Node() {
        this.zero = null;
        this.one = null;
        this.cnt = 0;
    }
}