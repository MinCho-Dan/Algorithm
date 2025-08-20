import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_16800_구구단걷기 {


	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            
            long N = Long.parseLong(in.readLine());
            long minMove = Long.MAX_VALUE;

            for (int i = 1; i <= N / i; i++) { // 제곱근 이하의 약수 찾기.
            	// 처음에 i * i <= N으로 했었는데 에러가 났다.
            	// -> i가 int형이라 오버플로우난듯.
            	// 생각해보니 이 case도 예전에 한번 경험해 봤던거다.
				if (N % i == 0) { // i가 N의 약수라면
					long j = N / i;
					// i*j=N이고, 최소 이동 횟수는 (i-1)(j-1)이다.
					long move = (i - 1) + (j - 1); 
					if (move < minMove) {
						minMove = move;
					}
 				}
			}
            
            sb.append(minMove + "\n");

        }
        System.out.println(sb);
	}


}

//public static void main(String[] args) throws Exception {
//
//	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//    StringBuilder sb = new StringBuilder();
//    
//    int T = Integer.parseInt(in.readLine());
//    
//    for (int test_case = 1; test_case <= T; test_case++) {
//        sb.append("#" + test_case + " ");
//        
//        long N = Long.parseLong(in.readLine());
//
//        double n = Math.sqrt(N);
//        long m = 1;
//        while (m*n < N) {
//        	m++;
//        }
//        long result = (long) (n-1)+(m-1);
//        
//        sb.append(result + "\n");
//
//    }
//    System.out.println(sb);
//}

// 테케 3번 100억 답이 달라서 폐기