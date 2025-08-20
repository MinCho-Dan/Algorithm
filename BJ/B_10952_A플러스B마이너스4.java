import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10952_A플러스B마이너스4 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(in.readLine());
		String temp;
		
		while ((temp = in.readLine()) != null) {
			String[] tmp = temp.split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			sb.append(a+b + "\n");
			
		}
		
		System.out.println(sb);
	}

}