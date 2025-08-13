package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S_6781_삼삼트리플게임 {
	
	// 6781. 삼삼 트리플 게임

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            
            // arr[0] = 빨강/ arr[1] = 초록 / arr[2] = 파랑
            // arr[색상][숫자]
            int[][] arr = new int[3][10];
            
            char[] tmpNum = in.readLine().toCharArray();
            char[] tmpColor = in.readLine().toCharArray();

            for (int i = 0; i < 9; i++) { // (char형 숫자 - '0')을 하면 int형 숫자 반환
				if (tmpColor[i] == 'R') {arr[0][tmpNum[i] - '0']++;}
				if (tmpColor[i] == 'G') {arr[1][tmpNum[i] - '0']++;}
				if (tmpColor[i] == 'B') {arr[2][tmpNum[i] - '0']++;}
			}
            
            for (int i = 0; i < 3; i++) { // R,G,B 3종류 색의
            	for (int j = 1; j < 10; j++) { // 1~9의 숫자카드 중
					while (arr[i][j] >= 3) {arr[i][j] -= 3;} // 트리플 삭제
					for (int k = 1; k < 8; k++) {
						while (arr[i][k]>0 && // 연속된 숫자 세개 처리.
								arr[i][k+1]>0 &&
								arr[i][k+2]>0) {
							arr[i][k]--;
							arr[i][k+1]--;
							arr[i][k+2]--;
						}
					}
				}
			}

            int result = Arrays.stream(arr[0]).sum() // 남은 카드 수
            		+ Arrays.stream(arr[1]).sum()
            		+ Arrays.stream(arr[2]).sum();
            
            // 남은 카드수가 0이면 Win, 아니면 Continue
            sb.append(result == 0 ? "Win\n" : "Continue\n");

        }
        System.out.println(sb);
	}


}
