import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_1218_괄호짝짓기 {
		
	private static String[] arr;
	private static int[] judge;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
            
            N = Integer.parseInt(in.readLine()); // 테케 길이
	        
	        arr = new String[N]; // 한줄 읽을 배열 String 배열 생성
	        arr = in.readLine().split("");
	        
	        judge = new int[8]; // 개수를 세는 배열 생성
//	        '()', '[]', '{}', '<>'
	        
	        
	        for (int i=0; i<N; i++) {// 개수 카운트
	        	if (arr[i].equals("(")) {judge[0] += 1;continue;}
	        	if (arr[i].equals(")")) {judge[1] += 1;
	        	continue;}
	        	if (arr[i].equals("[")) {judge[2] += 1;
	        	continue;}
	        	if (arr[i].equals("]")) {judge[3] += 1;
	        	continue;}
	        	if (arr[i].equals("{")) {judge[4] += 1;
	        	continue;}
	        	if (arr[i].equals("}")) {judge[5] += 1;
	        	continue;}
	        	if (arr[i].equals("<")) {judge[6] += 1;
	        	continue;}
	        	if (arr[i].equals(">")) {judge[7] += 1;
	        	continue;}
	        }
	        int end = -1;
	        
	        for (int i = 0; i < 8 ; i = i+2) {
	        	if (judge[i]-judge[i+1] != 0) { // 짝이 맞지 않으면 유효하지 않음
	        		end = 0;
	        		break;
	        	}
	        	else {
	        		end = 1;
	        	}
	        }

	        sb.append(end + "\n");
        }
        System.out.println(sb);
	}

	
}
