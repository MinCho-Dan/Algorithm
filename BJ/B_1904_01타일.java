import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1904_01타일 {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int N = Integer.parseInt(br.readLine());
	    
	    if (N==1) {
	    	System.out.println(1);
	    	return;
	    }
	    
		int[] dp = new int[N + 1];
		

		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i < N + 1; i++) {
			dp [i] = (dp[i - 1] + dp [i - 2]) % 15746; // 문제 조건. 15746 나머지 출력.
		}
		
		System.out.println(dp[N]);
        
    }


}


/* 
 * N=1 / 1개 "1" 
 * N=2 / 2개 "11" "00"  -> "10"과 "01"은 불가능함.
 * N=3 / 3개 "100" "001" "111" 
 * N=4 / 5개 "1001" "0011" "1100" "0000" "1111" 
 * N=5 / 8개 "11100" "11001" "10011" "00111" "10000" "00100" "00001" "11111"
 * 
 * N >= 3일 때 F(N) = F(N-2) + F(N-1)이 성립한다.
 * 
 * */