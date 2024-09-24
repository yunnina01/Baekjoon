import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[] arr = new char[N];
		for(int i = 0; i < N; i++)
			arr[i] = br.readLine().charAt(0);

		String res = "";
		int left = 0;
		int right = N - 1;
		while(left <= right) {
			if(arr[left] < arr[right]) 
				res += arr[left++];
			else if(arr[left] > arr[right]) 
				res += arr[right--];
			else {
				int low = left;
				int high = right;
				boolean isSame = true;
				while(low <= high) {
					if(arr[low] < arr[high]) {
						isSame = false;
						res += arr[left++];
						break;
					} else if(arr[low] > arr[high]) {
						isSame = false;
						res += arr[right--];
						break;
					} else {
						low++;
						high--;
					}
				}
				if(isSame)
					res += arr[left++];
			}
		}

		if(res.length() > 80) {
			String longRes = "";
			for(int i = 1; i <= res.length(); i++) {
				longRes += res.charAt(i - 1);
				if(i % 80 == 0)
                    longRes += "\n";
			}
			System.out.println(longRes);
			return;
		}
		System.out.println(res);
	}
}