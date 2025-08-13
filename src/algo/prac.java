package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class prac {

	private static int N;
	private static int R;
	private static int totalCnt;
	private static int[] num;
	private static int[] input;
	private static int[] sel;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		R = Integer.parseInt(in.readLine());
		totalCnt = 0;
		sel = new int[N];
		num = new int[R];
		input = new int[N];
		String[] tmp = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(tmp[i]);
		}

		permutation(0);
		System.out.println("순열의 총 경우의 수는 : " + totalCnt);
		totalCnt = 0;

		comb(0, 0);
		System.out.println("조합의 총 경우의 수는 : " + totalCnt);

	}

	private static void comb(int cnt, int start) {
		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				System.out.print(num[i] + " ");
			}
			System.out.println();
			totalCnt++;
			return;
		}

		for (int i = start; i < N; i++) {
			num[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}

	private static void permutation(int cnt) {

		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				System.out.print(num[i] + " ");
			}
			System.out.println();
			totalCnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (sel[i] == 1)
				continue;

			num[cnt] = input[i];
			sel[i] = 1;
			permutation(cnt + 1);
			sel[i] = 0;
		}

	}

}
