import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10818_최소최대 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		String[] tmp = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(tmp[i]);
			if (a < min) min = a;
			if (a > max) max = a;
		}
		
		System.out.println(min + " " + max);
	}
}
