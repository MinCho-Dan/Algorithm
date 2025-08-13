package com.ssafy.daily;

import java.util.Scanner;

public class M_15650_N과M2 {
	
	private static int N;
	private static int R;
	private static int[] numbers;
	
	private static int[] input;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		}
		
		combination(0, 0);
		sc.close();
	}
	
	
	// cnt : 직전까지 뽑은 조합에 포함된 원소 개수
	// start : 뽑을 원소의 인덱스 시작 번호
	private static void combination(int cnt, int start) {
		
		// 기저부분
		if (cnt == R) {
			for (int i = 0; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println("");
			return;
		}
		
		
		//유도부분
		for (int i = start; i < N; i++) {
			
			//원소 뽑기
			numbers[cnt] = input[i];
			
			// 다음 원소 뽑으러 가기
			combination(cnt +1, i + 1);
		}
		
	}

	
}
