import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class M_1260_DFS와BFS {
	

	private static int N; // 정점개수
	private static int M;
	private static int V;
	private static int[][] adjMatrix; // 인접행렬
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt()-1;
		
		adjMatrix = new int [N][N];
		
		int from, to;
		
		for (int i = 0; i < M ; i++) {
			from = sc.nextInt() -1;
			to = sc.nextInt() -1;
			
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
			
		dfs(V, new boolean[N]); 
		System.out.println(); // 줄바꾸기용
		bfs(V);
		
		
	    
	}

	private static void dfs(int current, boolean[] visited) {
		
		// 현재 정점 방문처리
		visited[current] = true;
		
		// 방문한 정점에서 해야할 일 작성
		System.out.print(current+1 + " ");
		
		//자신과 인접한 정점 방문
		for (int i = 0; i < N; i++) {
			if (adjMatrix[current][i] != 0  // 인접 확인
					&& !visited[i]) { // 방문여부 확인
				dfs(i, visited); // 정점 i 방문
			}
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>(); // 큐 선언
		boolean[] visited = new boolean[N]; // 방문체크용 배열
		
		// 첫번째 정점 방문 예약
		queue.offer(start); 
		visited[start] = true; // 방문 예약시 방문처리
		
		int current = start; // current를 시작 정점으로 초기화.
		while (!queue.isEmpty()) {
			
			// 방문예약 한 것 중 맨 앞에 있는 정점
			current = queue.poll();
			
			// 방문한 정점에서 해야할 일 처리 -> 예시는 알파벳 출력.
			System.out.print(current+1 + " ");
			
			// for문을 이용해서 0번 정점부터 N-1번 정점까지 탐색
			for (int i = 0; i < N; i++) {
				if (adjMatrix[current][i] != 0 && // 인접여부 확인
						!visited[i]) { // 방문여부 체크
					
					// 다음 정점을 방문예약.
					queue.offer(i);
					visited[i] = true; // 첫정점과 마찬가지로 방문예약시 방문처리
				}
			}
		}
	
	}
}
