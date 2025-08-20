import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_15552_빠른A플러스B {

	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < T; i++) {
			String[] tmp = in.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			sb.append(a+b + "\n");
		}
		
		System.out.println(sb);
	}

}
