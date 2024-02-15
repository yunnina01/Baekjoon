import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Wire> wires = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            wires.add(new Wire(A, B));
        }
        wires.sort((o1, o2) -> o1.a - o2.a);

        List<Integer> LIS = new ArrayList<>();
        int[] length = new int[N];
        for(int i = 0; i < N; i++) {
            int idx = -Collections.binarySearch(LIS, wires.get(i).b) - 1;
            if(idx < LIS.size())
                LIS.set(idx, wires.get(i).b);
            else
                LIS.add(wires.get(i).b);
            length[i] = idx + 1;
        }
        sb.append(N - LIS.size() + "\n");

        Set<Integer> set = new HashSet<>();
        int len = LIS.size();
        for(int i = N - 1; i >= 0; i--) {
            if(length[i] == len) {
                set.add(wires.get(i).b);
                if(--len == 0)
                    break;
            }
        }

        List<Integer> deleteWire = new ArrayList<>();
        for(Wire wire : wires) {
            if(!set.contains(wire.b))
                deleteWire.add(wire.a);
        }

        deleteWire.sort((o1, o2) -> o1 - o2);
        for(int a : deleteWire)
            sb.append(a + "\n");
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

class Wire {
    int a, b;

    Wire(int a, int b) {
        this.a = a;
        this.b = b;
    }
}