

// 1208. [S/W 문제해결 기본] 1일차 - Flatten

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_D3_1208_Flatten {
	
	private static int[] w; // 가로 100의 배열

	public static void main(String[] args) throws Exception {

        /**
         * 0. 입력파일 읽어들이기
         */
//        System.setIn(new FileInputStream("input1208.txt")); // 파일입력설정 (setIn)
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
            
            /**
             * 1. 입력파일 객체화
             */
            int N;
            N = Integer.parseInt(in.readLine()); // 덤프횟수

            String[] arr = in.readLine().split(" ");// 상자높이 String 배열에 넣기
            
            int[] w = new int [100]; // Int배열 생성

            for (int i = 0; i < 100; i++) { 
            	int tmp = Integer.parseInt(arr[i]); // 상자높이 Int 배열에 넣기
            	w[i] = tmp;
            }

            /**
             * 2. 알고리즘 풀기
             */
            int cnt = 0; // 덤프횟수를 세기위한 변수
            
            while (true) {
            	Arrays.sort(w); // 오름차순 정렬

            	if (w[99] - w[0] <= 1) { // Flatten 되었다면
            		sb.append(w[99] - w[0]);
            		break;
            	}
            	if (cnt >= N) { // 덤프 횟수를 초과한 경우
            		sb.append(w[99] - w[0]);
            		break;
            	}
            	
            	// 위의 두 가지가 아니라면, 덤프 실행 + 횟수 카운트
            	w[99]--;
				w[0]++;
				cnt++;
            }
            sb.append("\n");
        }
        System.out.println(sb);
	}
}
