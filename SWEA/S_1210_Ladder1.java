package com.ssafy.daily;

// 자동 import Ctrl + Shift + O
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_1210_Ladder1 {
	
	private static int[][] arr; // 100x100의 배열
	private static String[] tmp;
	private static int x;
	private static int y;

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
	        // (x,y) 좌표라고 생각하고 짜도록 한다...
	        y = 98;
	        x = 0;
	        
	        for (int i=0; i < 100; i++) { // 시작점 2 찾기
	        	if (arr[99][i] == 2) {
	        		x = i;
	        		break;
	        	}
	        }
	        
	        while (true) {
	        	
	        	if (x-1 >= 0 && arr[y][x-1] == 1) { // 좌측 확인
	        		x--;
	        		while (true) {
	        			if (x-1 == -1 || arr[y][x-1] == 0) { // 세로막대까지 좌측이동
	        				break;
	        			}
	        			x--;
	        		}
	        	}
	        	
	        	else if ((x+1 < 100 && arr[y][x+1] == 1)) { // 우측확인
	        		x++;
	        		while (true) {
	        			if (x+1 == 100 || arr[y][x+1] == 0) { // 세로막대까지 우측이동
	        				break;
	        			}
	        			x++;
	        		}
	        	}
	        	
	        	if (y==0) {
	        		break; // 최상단에 왔다면 while문 탈출
	        	}
	        	
	        	y--;
	        }
	        
	        sb.append(x + "\n");
	        
        }// 테케 for문 끝
        System.out.println(sb);
	}
}
