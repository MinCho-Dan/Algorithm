package com.ssafy.daily;

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_1220_Magnetic {
	
	private static int[][] arr; // 100x100의 배열
	private static String[] tmp;
	private static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
            
	        in.readLine(); // 테케번호 날리기용
	        
	        tmp = new String[100]; // 한줄 읽을 배열 String 배열 생성
	        arr = new int[100][100];
	        
	        for (int i=0; i < 100; i++) {
	        	tmp = in.readLine().split(" "); // tmp배열에 1줄(100개) 넣기
	        	for (int j=0; j < 100; j++){
	        		int temp = Integer.parseInt(tmp[j]);
	        		arr[i][j] = temp; // 2중 배열 arr 생성
	        	}
	        }
	        
        	int n = 0;
        	result = 0;
        	
	        for (int i=0; i < 100; i++) {
	        	for (int j=0; j < 100; j++) {
	        		if (arr[j][i] == 1 && n == 0) { // 열방향 N극 탐색.
	        			n = 1; // n극 만남 표시 변수
	        		}
	        		else if (n == 1 && arr[j][i] == 2) { // N극을 만난 이후 S극이 있는지 탐색
	        			n = 0; // 만남표시 초기화
	        			result++; // S극 발견시 result +1
	        		}
	        	}
	        	n = 0; // n극을 만나고 마지막까지 s극을 못만난 경우 초기화
	        }
	        sb.append(result + "\n");
        }
        System.out.println(sb);
	}
}
