import java.io.*;

public class Main {
    static final double PI = 3.141592653589793238462643;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nFac = br.readLine();

        double lnFac, lnNFac;
        if(nFac.length() > 18)
            lnNFac = Math.log(Long.parseLong(nFac.substring(0, 10))) + (nFac.length() - 10) * Math.log(10);
        else
            lnNFac = Math.log(Long.parseLong(nFac));

        for(int n = 1;; n++) {
            lnFac = 0.5 * Math.log(2 * PI * n) + n * Math.log(n) - n;
            if(lnFac - lnNFac > -0.5) {
                System.out.println(n);
                break;
            }
        }
    }
}