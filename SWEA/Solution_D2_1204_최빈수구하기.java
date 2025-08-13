

//1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_D2_1204_최빈수구하기 {
	
	private static int[] counter; // 빈도 수를 저장할 배열
	
	
	public static void main(String[] args) throws Exception {
		
        /**
         * 0. 입력파일 읽어들이기
         */
//        System.setIn(new FileInputStream("input.txt")); // 파일입력설정 (setIn)
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        int T;
        T = Integer.parseInt(in.readLine());
        
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");

            // Java 구현 문제.
            // 점수를 index로. 101칸짜리 array 생성.
            // array에서 카운팅.
            /**
             * 1. 입력파일 객체화
             */
            counter = new int [101]; // 0~100점까지 
            
            in.readLine(); // 테케 번호부분 건너뛰기
            String[] scores = in.readLine().split(" ");

            /**
             * 2. 알고리즘 풀기
             */
            for (int i = 0; i < scores.length; i++) { // counter배열에 카운팅하기
            	int score = Integer.parseInt(scores[i]);
            	counter[score]++;
            }
            
            /**
             * 3. 정답 출력
             */
            int max = Integer.MIN_VALUE; // 최소값으로 초기화
            int answer = -1;
            for (int score = 0; score <= 100; score++) {
            	
            	// 최빈수가 여러 개일때는 점수가 가장 큰 점수 : 등호포함
            	if (max <= counter[score]) {
            		max = counter[score];
            		answer = score;
            	}
            }
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb);
    }

}
