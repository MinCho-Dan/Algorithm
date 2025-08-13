package com.ssafy.daily;

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_1209_Sum {
	
	private static int[][] arr; // 100x100의 배열
	private static String[] tmp;
	private static int[] sumArr;

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
	        
	        sumArr = new int[202]; // 가로100/세로100/대각선2개 배열 생성
	        
	        // 가로방향(행) 덧셈
	        for (int i=0; i<100; i++) {
	        	for (int j=0; j<100; j++) {
	        		sumArr[i] += arr[i][j];
	        	}
	        }


	        // 세로방향(열) 덧셈
	        for (int i=0; i<100; i++) {
	        	for (int j=0; j<100; j++) {
	        		sumArr[i+100] += arr[j][i];
	        	}
	        }

	        // 대각선 덧셈(좌->우)
	        for (int i=0; i<100; i++) {
	        		sumArr[200] += arr[i][i];
	        }
	        
	        // 대각선 덧셈(우->좌)
	        for (int i=0; i<100; i++) {
        		sumArr[201] += arr[i][99-i];
	        }
	        
	        // 최대값 찾기
	        int max = 0;
	        for (int i=0; i<sumArr.length; i++) {
	        	if (max < sumArr[i]) {
	        		max = sumArr[i];
	        	}
	        }
	        sb.append(max+"\n");
        }
        System.out.println(sb);
	}
}
