import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10807_개수세기 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		String[] tmp = in.readLine().split(" ");
		int V = Integer.parseInt(in.readLine());
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			if (V==Integer.parseInt(tmp[i])) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}