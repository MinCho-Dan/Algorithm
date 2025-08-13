package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2563_색종이 {

	public static int[][] paper;
	public static int[] paperStart;
	public static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int C = Integer.parseInt(in.readLine()); // 색종이 수
		paper = new int[101][101];
		paperStart = new int[C*2];
		
		for (int i = 0; i < C*2; i = i+2) {
			String[] tmp = in.readLine().split(" "); // 시작점 찾기
			paperStart[i] = Integer.parseInt(tmp[0]);
			paperStart[i+1] = Integer.parseInt(tmp[1]);
		
			for (int j = 0; j < 10; j++) { // 시작점으로부터 10*10 색종이 부분 1로 만들기.
				for (int k = 0; k < 10; k++) {
				paper[paperStart[i]+j][paperStart[i+1]+k] = 1;
				}
			}
		}
		
		for (int i = 0; i < 101; i++) { // 도화지 위의 1 개수 세기
			for (int j = 0; j < 101; j++) {
				if (paper[i][j]==1) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}

