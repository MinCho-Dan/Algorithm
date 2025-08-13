import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_3289_서로소집합 {

	private static int N;
	private static int M;
	private static int[] set; // 서로소 집합
	
	private static int findSet(int a) {
		
		// 기저부분 : a가 대표면 대표자라고 리턴
		if (set[a] == a) {
			return a;
		}
		
		// 유도부분 : a가 대표가 아니면 대표자 찾으러 가기
//		return findSet(parents[a]); // 편향되는 depth가 깊은 그래프가 생김
		return set[a] = findSet(set[a]); // 경로 압축!
	}
	
	
	// a, b 두 집합 합치기
	private static boolean unionSet(int a, int b) {
		
		int aRoot = findSet(a); // a의 대표자 찾기
		int bRoot = findSet(b); // b의 대표자 찾기
		// 대표자가 같다면 이미 같은 집합. false 리턴.
		if (aRoot == bRoot) { 
			return false;
		}
		
		// 대표자가 다르면 하나의 집합으로 합치기
		// a의 대표자 집합으로 b 대표자 집합이 들어가는 방식
		set[bRoot] = aRoot;
		return true;
	}	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine()); // TC 개수
        
        // T개의 TC 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
             
            String[] tmp = in.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            set = new int[N + 1];
            for (int i = 0; i < N+1; i++) { // 서로소 집합 만들기
    			set[i] = i;
    		}
            
            M = Integer.parseInt(tmp[1]);
            for (int i = 0; i < M; i++) {
            	String[] temp = in.readLine().split(" ");
            	int f = Integer.parseInt(temp[0]);
            	int a = Integer.parseInt(temp[1]);
            	int b = Integer.parseInt(temp[2]);
            	
            	if (f == 0) { // f가 0일때 합집합
            		unionSet(a, b);
            	}
            	if (f == 1) { // f가 1일때 대표자가 같은지 확인하고 같으면 1 리턴
            		if (findSet(a)==findSet(b)) {
            			sb.append(1);
            		}
            		else sb.append(0); // 대표자가 다르면 0 리턴
            	}
			}
            
            sb.append("\n");
            
        }
        System.out.println(sb);
	}

}
