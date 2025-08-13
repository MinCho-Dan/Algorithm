package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_2806_NQueen {
	
	// 2806. N-Queen
	private static int[] board;
	private static int N, T, cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(in.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            
            N = Integer.parseInt(in.readLine());
            board = new int[N]; // (인덱스+1) = n번째 행 / 값 = 퀸이 놓인 열 번호
            
            cnt = 0;
            
            queen(0);
            
            sb.append(cnt + "\n");
            
        }
        System.out.println(sb);
	}

	// 백트래킹으로 퀸 놓기
	private static void queen(int r) {
		// 기저조건 : 모든 퀸을 다 놓았다면 하나의 경우의 수가 있다는 뜻.
		// cnt++해서 카운팅
		if (r == N) {
			cnt++;
			return;
		}
		
		// 유도 조건 : 현재 행(r)에 퀸을 놓을 열(c) 탐색
		for (int c = 0; c < N; c++) {
			if(possible(r,c)) { // 놓을 수 있는지 없는지 확인
				board[r] = c; // 놓을 수 있다면 (r,c) 자리에 퀸을 놓는다.
				queen(r + 1); // 다음행으로 이동 (재귀)
			}
		}
	}

	// 놓을 수 있는지 없는지?
	// 0. queen 함수가 행마다 하나씩 퀸을 놓으니까, 행을 볼 필요는 없다
	// 1. 같은열에 퀸이 있는지 본다.
	// 2. 대각선에 퀸이  있는지 본다. 
	private static boolean possible(int r, int c) {
		for (int i = 0; i < r; i++) {
			
			// 같은열에 퀸?
			if (board[i] == c) {return false;}
			
			// 대각선에 퀸?
			// 행차이(r1-r2의 절대값) == 열차이(c1-c2의 절대값) 이라면 대각선에 위치한다.
			if (Math.abs(i - r) == Math.abs(board[i] - c)) {return false;}
		}
		return true;
	}


}

/*
 * (N * N)의 보드판 위에 퀸을 N개 놓는 문제.
 * 퀸의 동선에 다른 퀸이 있으면 안된다.
 * 예전에 42에서 ten queen이라는 문제로 풀어봤던 문제
 * 그떈 n = 10인거 답만 출력하면 됐었어서 그냥 724 출력하는 꼼수도 있었음...ㅎㅎ....
 * 요약 : 그냥 백트래킹 갈기면 풀린다.
 */
