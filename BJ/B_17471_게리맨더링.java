import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_17471_게리맨더링 {

	static int N, Min, K, sum, partSum;
	static int[] population;
	static int[][] adj;
	static boolean[] visited;
	static boolean[] check;
	static int[] temp;
	
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	
	    // 인구수 리스트 초기화
	    String[] tmp = br.readLine().split(" ");
	    population = new int[N+1]; // 0번 사용안함
	    for (int i = 1; i < N + 1; i++) { // 그래서 1번부터 N까지.
	    	population[i] = Integer.parseInt(tmp[i-1]);
	    }
	    
	    // 인접행렬 생성
	    adj = new int[N + 1][N + 1];
	    for (int from = 1; from <= N; from++) {
            String[] temp = br.readLine().split(" ");
            int num = Integer.parseInt(temp[0]);
            for (int j = 1; j <= num; j++) {
            	int to = Integer.parseInt(temp[j]);
            	adj[from][to] = 1;
            	adj[to][from] = 1;
            }
        }
	    
        // 조합으로 선거구 분할 및 탐색
	    Min = Integer.MAX_VALUE;
	    
	    // N개의 구역 중 1개부터 N/2개까지 선택하는 모든 조합을 탐색
        // 그 이상은 중복 탐색이기에 그를 피하기 위함
        for (int i = 1; i <= (N / 2); i++) {
            K = i; // 현재 조합으로 선택할 구역의 개수
            check = new boolean[N + 1];
            pick(1, 0); // 조합 탐색 시작
        }

        // N이 홀수일 경우 N/2+1개를 선택하는 경우도 포함
        if (N % 2 != 0) {
            K = (N / 2) + 1;
            check = new boolean[N + 1];
            pick(1, 0);
        }
        
        //결과 출력
        System.out.println(Min == Integer.MAX_VALUE ? -1 : Min);
        
    }
	    
	// 조합으로 선거구 뽑는 메서드
	public static void pick(int start, int cnt) { 
        // 기저부분
		if (cnt == K) { // K개의 구역을 모두 선택한 경우
            // 조합이 완성되면 유효성 검사를 위해 temp 배열에 현재 선거구 분할 상태를 저장
            temp = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                if (check[i]) {temp[i] = 1;} // 1번 선거구에 속함}
                else {temp[i] = 0;} // 2번 선거구에 속함
            }
            confirm(); // 두 선거구가 유효한지 확인하고 인구 차이 계산
            return;
        }
        
        // 유도부분
        for (int i = start; i < N + 1; i++) {
            check[i] = true; // i번 구역 선택
            pick(i + 1, cnt + 1); // 다음 구역 탐색
            check[i] = false; // i번 구역 미선택 (백트래킹)
        }
    }
	
	// 1,2번 선거구의 연결이 되는지 확인하고 인구수 계산하는 메서드
	public static void confirm() {
        visited = new boolean[N + 1];
        int people1 = 0; // 1번 선거구 인구수
        int people2 = 0; // 2번 선거구 인구수

        // 1번 선거구의 연결성을 확인하고 인구수를 계산
        for (int i = 1; i < N + 1; i++) {
            if (temp[i] == 1 && !visited[i]) {
                partSum = 0;
                dfs(i); 
                people1 = partSum;
                break; // 1번 선거구는 하나의 연결된 덩어리이므로, 첫 번째 구역에서 탐색 후 종료
            }
        }
        
        // 2번 선거구의 연결성을 확인하고 인구수를 계산
        for (int i = 1; i < N + 1; i++) {
            if (temp[i] == 0 && !visited[i]) {
                partSum = 0;
                dfs(i); 
                people2 = partSum;
                break; // 2번 선거구도 하나의 연결된 덩어리이므로, 첫 번째 구역에서 탐색 후 종료
            }
        }

        // 모든 구역이 방문되었는지 확인하여 두 선거구가 각각 연결된 상태인지 판별
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                return;
            }
        }

        // 모든 구역이 연결되어 있다면, 두 선거구의 인구 차이 계산 후 최솟값 갱신
        sum = Math.abs(people2 - people1);
        Min = Math.min(Min, sum);
    }

	public static void dfs(int x) {
		visited[x] = true;
	    partSum += population[x]; // 현재 구역의 인구수를 더함
	        
	    // 현재 구역과 연결된 모든 구역을 탐색
	    for (int i = 1; i < N + 1; i++) {
	        // 이웃 구역이 아직 방문되지 않았고, 현재 구역과 같은 선거구에 속하며, 연결되어 있다면
	    	if (!visited[i] && temp[i] == temp[x] && adj[i][x] == 1) {
	    		dfs(i); // 재귀 호출로 DFS 탐색 계속
	        }
	    }
	}
	
	
}

/*
 * 선거구가 N개.
 * N개의 선거구를 NC1 ~ NC(2/N)까지 조합을 구하고
 * 2개의 선거구로 나눈 다음
 * 연결이 되는지 DFS로 조사.
 * DFS로 조사할 때 인구수 값 차이를 저장.
 * 그 중에 최소값을 리턴하면? 풀릴거 같은데.
 */


