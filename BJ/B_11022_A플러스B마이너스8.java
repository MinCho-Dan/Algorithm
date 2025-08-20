import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_11022_A플러스B마이너스8 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int i = 1; i < T+1; i++) {
			sb.append("Case #" + i + ": ");
			
			String[] tmp = in.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			sb.append(a + " + " + b + " = " + (a+b) + "\n");
		}
		
		System.out.println(sb);
	}

}