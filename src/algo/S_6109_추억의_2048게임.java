package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_6109_추억의_2048게임 {
	
	private static int[][] board;
	private static int T;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(in.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + "\n");
            
            String[] tmp = in.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            String direction = tmp[1];
            board = new int[N][N];
            
            for (int i = 0; i < N; i++) {
            	String[] temp = in.readLine().split(" ");
            	for (int j = 0; j < N; j++) {
            		board[i][j] = Integer.parseInt(temp[j]);
            	}
			}
            
            // 방향에 따라 보드 회전
            if (direction.equals("up")) {
                board = rotate(board, 3); // 시계방향 3번 (반시계 방향 90도)
            } else if (direction.equals("down")) {
                board = rotate(board, 1); // 시계 방향 90도
            } else if (direction.equals("right")) {
                board = rotate(board, 2); // 180도 회전
            }
            
            // 왼쪽으로 이동 로직
            moveLeft();
            
            // 원래 방향으로 다시 회전
            if (direction.equals("up")) {
                board = rotate(board, 1); // 시계 방향 90도
            } else if (direction.equals("down")) {
                board = rotate(board, 3); // 시계방향 3번 (반시계 방향 90도)
            } else if (direction.equals("right")) {
                board = rotate(board, 2); // 180도 회전
            }
            
            // 결과값 sb에 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j]).append(" ");
                }
	        sb.append("\n");
            }
        
        }
        System.out.println(sb);

}

	// 왼쪽으로 밀기 메서드
	private static void moveLeft() { 
		for (int i = 0; i < N; i++) {
            int[] tmp = new int[N]; // 행을 담는 임시 배열
            int idx = 0;
            boolean[] merged = new boolean[N]; // 합침여부 확인
            
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) { // 비어있지 않다면
                    if (idx > 0 && tmp[idx - 1] == board[i][j] && // 만나는 수가 동일하고,
                    		!merged[idx - 1]) { // 아직 합쳐지지 않았다면,
                        tmp[idx - 1] *= 2; // tmp 배열에 두배로 만들고 담기
                        merged[idx - 1] = true; // 합쳐짐 표시
                    } else {
                        tmp[idx++] = board[i][j]; //왼쪽부터 순서대로 임시배열에 담기
                    }
                }
            }
            board[i] = tmp; // i행을 왼쪽으로 밀어낸 배열로 덮어쓰기
        }
	}

	// 보드 회전 (90도 시계방향으로 회전하는걸 기준) 메서드
	private static int[][] rotate(int[][] arr, int cnt) {
		int[][] result = arr;
		for (int k = 0; k < cnt; k++) { // cnt만큼 반복해서 회전
            int[][] tmp = new int[N][N]; // tmp배열에 arr을 회전한걸 카피하는 방식
            
            // 이중for문으로 시계방향 회전시키기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmp[j][(N - 1) - i] = result[i][j];
                }
            }
            result = tmp;
        }
		
		return result;
	}
}