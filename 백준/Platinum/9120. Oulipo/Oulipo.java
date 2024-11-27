import java.io.*;

public class Main {
    static char[] W, T;
    static int[] pi;
    static int len, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

    	while(TC-- > 0) {
    		T = br.readLine().toCharArray();
	        W = br.readLine().toCharArray();
	        len = T.length;
            res = 0;
    		
	        getPi();
	        kmp();
	        System.out.println(res);
    	}
    }

    static void getPi() {
        pi = new int[T.length];
        for(int i = 1, j = 0; i < T.length; i++) {
            while(j > 0 && T[i] != T[j])
                j = pi[j - 1];
            if(T[i] == T[j])
                pi[i] = ++j;
        }
    }

    static void kmp() {
        for(int i = 0, j = 0; i < W.length; i++) {
            while(j > 0 && W[i] != T[j])
                j = pi[j - 1];
            if(W[i] == T[j]) {
                if(j == len - 1) {
                    res++;
                    j = pi[j];
                } else
                    j++;
            }
        }
    }
}