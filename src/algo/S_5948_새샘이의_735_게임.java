package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class S_5948_새샘이의_735_게임 {
	
	// 5948. 새샘이의 7-3-5 게임
	private static int[] input;
	private static int T;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(in.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            
            // 7개의 수 저장하기
            input = new int[7];
            String[] tmp = in.readLine().split(" ");
            for (int i = 0; i < 7; i++) {
				input[i] = Integer.parseInt(tmp[i]);
			}
            
            // 합의 중복값 제거를 위해 HashSet 사용
            HashSet<Integer> sums = new HashSet<>();
            
            // 3중 for문으로 7C3 구하기
            // 재귀로하면 좋지만 이 문제는 7C3이라고 딱 정해져있어서..
            for (int i = 0; i < 7; i++) {
                for (int j = i + 1; j < 7; j++) {
                    for (int k = j + 1; k < 7; k++) {
                        int sum = input[i] + input[j] + input[k];
                        sums.add(sum);
                    }
                }
            }

            // 값들을 정렬하기 위해 List로 옮기기
            ArrayList<Integer> sortedSums = new ArrayList<>(sums);
            Collections.sort(sortedSums, Collections.reverseOrder());// 내림차순
           
	        sb.append(sortedSums.get(4)+"\n"); // 4번인덱스 = 5번쨰로 큰 값
        }
        System.out.println(sb);
	}


}



/* 
 * 7C3으로 7개중 3개 뽑는 조합의 합을 구해서
 * 그 중 5번째로 큰 값을 출력하는 문제.
 * 근데 이거 중복된 값 있으면 어떻게 출력하는지 문제에 안나와있는데
 * 댓글보니까 중복 지워야 한대서 set 이용해서 중복 제거
 * 
 * */