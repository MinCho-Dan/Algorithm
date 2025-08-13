package src;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class M_13023_ABCDE {
	
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static boolean found; // 길이 4의 친구 관계를 찾았는지 확인
	static int N;
	
	public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);

    // 인접 리스트 초기화
    adjList = new ArrayList[N];
    for (int i = 0; i < N; i++) {
        adjList[i] = new ArrayList<>();
    }

    // 친구 관계 입력받아 그래프 구성 (양방향)
    for (int i = 0; i < M; i++) {
        input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        adjList[a].add(b);
        adjList[b].add(a);
    }

    found = false; // depth가 4가 되는 친구 관계가 있는지 체크하는 플래그임.
    // 모든 사람을 시작점으로 DFS 탐색
    for (int i = 0; i < N; i++) {
        // 문제에서 요구하는 5명(길이 4)을 찾았으면 더 이상 탐색할 필요가 없음
        if (!found) {
            visited = new boolean[N]; // 매 시작점마다 방문 배열을 초기화해야 다른 경로를 탐색 가능
            dfs(i, 0);
        }
    }

    // 결과 출력
    System.out.println(found ? 1 : 0);
}

	/**
	 * DFS 탐색을 수행하는 재귀 함수
	 * @param person 현재 탐색 중인 사람의 번호
	 * @param depth 현재까지 이어진 친구 관계의 길이 (0부터 시작)
	 */
	public static void dfs(int person, int depth) {
	    // 기저 조건
	    // 친구 관계의 길이가 4가 되면 (총 5명 연결)
	    if (depth == 4) {
	        found = true;
	        return;
	    }
	
	    // 이미 결과를 찾았으면 더 이상 탐색하지 않고 즉시 종료
	    if (found) {
	        return;
	    }
	    
	    // 현재 사람 방문 처리
	    visited[person] = true;
	
	    // 현재 사람의 모든 친구들을 순회하며 탐색
	    for (int friend : adjList[person]) {
	        // 아직 방문하지 않은 친구라면
	        if (!visited[friend]) {
	            dfs(friend, depth + 1); // 재귀 호출로 다음 친구 탐색
	            
	            // 재귀 호출 후 돌아왔을 때, 이미 결과를 찾았으면 즉시 종료
	            if (found) {
	                return;
	            }
	        }
	    }
	    
	    // 현재 경로의 탐색이 끝났으므로,
	    // 다른 경로에서 이 사람을 다시 방문할 수 있도록
	    // 방문 표시를 해제해야함
	    visited[person] = false;
	}
}
