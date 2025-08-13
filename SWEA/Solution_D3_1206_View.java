

// 1206. [S/W 문제해결 기본] 1일차 - View

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_D3_1206_View {
	
	private static int[] h; // 건물 높이를 저장할 배열
	
	
	public static void main(String[] args) throws Exception {
		
        /**
         * 0. 입력파일 읽어들이기
         */
//        System.setIn(new FileInputStream("input1206.txt")); // 파일입력설정 (setIn)
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");


            /**
             * 1. 입력파일 객체화
             */
            int N;
            N = Integer.parseInt(in.readLine());
            
            h = new int [N]; // N개 길이의 Int배열 생성

            String[] arr = in.readLine().split(" ");// 건물높이 String 배열에 넣기

            for (int i = 0; i < arr.length; i++) { 
            	int tmp = Integer.parseInt(arr[i]); // 건물높이 Int 배열에 넣기
            	h[i]= tmp;
            }

            /**
             * 2. 알고리즘 풀기
             */
            int temp[]; // 양옆 4개 빌딩 대소 비교용
            temp = new int[4];
            
            int max = 0; // 양옆 4개 빌딩 중 최대값

            int result = 0; // 결과값 변수 선언
            
            for (int i = 2; i < arr.length - 1; i++ ) {
            	if (h[i-2] < h[i] && // 양옆 2/2개가 가리는지 아닌지 판별
            		h[i-1] < h[i] && 
            		h[i+1] < h[i] && 
            		h[i+2] < h[i]) {
	            		temp[0] = h[i-2]; // 양옆 2/2개 임시로 담은 temp 배열 생성
	            		temp[1] = h[i-1];
	            		temp[2] = h[i+1];
	            		temp[3] = h[i+2];
	            		if (max == 0) {max = temp[0];}
	        		
	            		for (int k = 0; k < 3; k++) {
	            			if (max < temp[k+1]) { // temp 배열 내의 max값 찾기.
	            				max = temp[k+1];
	            			}
	            		}
	            		result += (h[i] - max); // i번째 건물과 주변 2/2개건물 최대값의 차이를 result에 추가
	            		max = 0;
            	}
            }
            
            /**
             * 3. 정답 출력
             */
            sb.append(result).append("\n");
        }
        
        System.out.println(sb);
    }

}
