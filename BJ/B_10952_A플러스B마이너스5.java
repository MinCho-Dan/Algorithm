import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10952_A플러스B마이너스5 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(in.readLine());
		
		while (true) {
//			sb.append("Case #" + i + ": ");
			
			String[] tmp = in.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			if (a==0 && b==0) break;
//			sb.append(a + " + " + b + " = " + (a+b) + "\n");
			sb.append(a+b + "\n");
		}
		
		System.out.println(sb);
	}

}