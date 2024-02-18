import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

    	PriorityQueue<Rail> hq = new PriorityQueue<>();
        PriorityQueue<Rail> oq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
        	hq.add(new Rail(h, i));
        	oq.add(new Rail(o, i));
        }
        int d = Integer.parseInt(br.readLine());
        
        PriorityQueue<Rail> ln = new PriorityQueue<>();
        PriorityQueue<Rail> rn = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> dup = new HashSet<>();
        int res = 0;

        while(!hq.isEmpty() || !oq.isEmpty()) {
        	int min = Integer.MAX_VALUE;
        	if(!hq.isEmpty())
                min = Math.min(min, hq.peek().v);
        	if(!oq.isEmpty())
                min = Math.min(min, oq.peek().v);
        	if(!ln.isEmpty())
                min = Math.min(min, ln.peek().v);
        	if(!rn.isEmpty())
                min = Math.min(min, rn.peek().v);
        	int end = min + d;
            
            Rail tmp;
        	while(!hq.isEmpty() && (tmp = hq.peek()).v <= end) {
        		if(set.contains(tmp.idx))
                    dup.add(tmp.idx);
        		else
                    set.add(tmp.idx);
        		ln.add(hq.poll());
        	}
        	while(!oq.isEmpty() && (tmp = oq.peek()).v <= end) {
        		if(set.contains(tmp.idx))
                    dup.add(tmp.idx);
        		else
                    set.add(tmp.idx);
        		rn.add(oq.poll());
        	}
        	res = Math.max(res, dup.size());

        	if(ln.isEmpty() || rn.isEmpty()) {
        		if(!ln.isEmpty()) {
        			int now = ln.poll().idx;
        			dup.remove(now);
        			set.remove(now);
        		} else if(!rn.isEmpty()) {
        			int now = rn.poll().idx;
        			dup.remove(now);
        			set.remove(now);
        		}
        	} else {
	        	if(ln.peek().v > rn.peek().v) {
	        		int now = rn.poll().idx;
        			dup.remove(now);
        			set.remove(now);
	        	} else {
	        		int now = ln.poll().idx;
        			dup.remove(now);
        			set.remove(now);
	        	}
        	}
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}

class Rail implements Comparable<Rail> {
    int v, idx;

    Rail(int v, int idx) {
        this.v = v;
        this.idx = idx;
    }

    @Override
    public int compareTo(Rail o) {
        return this.v - o.v;
    }
}