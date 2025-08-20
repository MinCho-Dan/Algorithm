import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2562_최댓값 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		
		for (int i = 0; i < 9; i++) {
			int a = Integer.parseInt(in.readLine());
			if (a > max) {
				max = a;
				maxIdx = i+1;
			}
		}
		System.out.println(max);
		System.out.println(maxIdx);
	}
}
