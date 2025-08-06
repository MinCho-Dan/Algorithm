package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BaseCode {

	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
            
            int N = Integer.parseInt(in.readLine());
            
            
           
	        sb.append("\n");
        }
        System.out.println(sb);
	}


}
