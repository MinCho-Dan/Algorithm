import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10871_X보다작은수 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = in.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int X = Integer.parseInt(temp[1]);
		
		String[] tmp = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(tmp[i]);
			if (a < X) {
				System.out.print(a + " ");
			}
		}
	}
}
