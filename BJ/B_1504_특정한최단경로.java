import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1504_특정한최단경로 {
	
	private static Vertex[] adjList;
	
	private static class Vertex {
		public int vertex;
		public int weight;
		public Vertex link;
		
		public Vertex(int vertex, int weight, Vertex link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점 수
		int E = Integer.parseInt(st.nextToken()); // 간선개수
		
		adjList = new Vertex[V+1];

		for (int i = 1; i < E + 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Vertex(to, weight, adjList[from]);
			adjList[to] = new Vertex(from, weight, adjList[to]);
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int end1 = Integer.parseInt(st.nextToken()); // 출발 정점
		int end2 = Integer.parseInt(st.nextToken()); // 도착 정점
		int a = 0, b = 0;
		
		int result11 = findShort(V, E, 1, end1);
		int result12 = findShort(V, E, end1, end2);
		int result13 = findShort(V, E, end2, V);
		if (result11 != -1 &&
			result12 != -1 &&
			result13 != -1) {
			a = result11 + result12 + result13;
		}
		
		int result21 = findShort(V, E, 1, end2);
		int result22 = findShort(V, E, end2, end1);
		int result23 = findShort(V, E, end1, V);
		if (result21 != -1 &&
			result22 != -1 &&
			result23 != -1) {
			b = result21 + result22 + result23;
		}
		
		if ( a > 0 && b > 0 ) {
			System.out.println(Integer.min(a, b));
		}
		else if (a>0) System.out.println(a);
		else if (b>0) System.out.println(b);
		else System.out.println(-1);
	}
	
	
	// 다익스트라 알고리즘 구현
	public static int findShort (int V, int E, int start, int end) {
		int[] distance = new int[V+1]; // 출발지에서 자신으로 오는데 소요되는 최소비용 기록
		boolean[] visited = new boolean[V+1]; // 정점 고려 여부
		Arrays.fill(distance, Integer.MAX_VALUE); // 무한대 값으로 초기화
		
		// 출발 정점 처리
		distance[start] = 0; // 출발점 비용 0으로 처리
		
		int min; // 선택된 최소비용
		int current; // 선택된 정점
		
		for (int cnt = 1; cnt < V+1; cnt++) {
			
			// 1단계 : 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점선택
			// 방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			current = -1;
			min = Integer.MAX_VALUE;
			
			for (int i = 1; i < V+1; i++) {
				if(!visited[i] && distance[i] < min) {
					min = distance[i];
					current = i;
				}
			}
			
			// (선택사항) 가지치기 : 정점을 못 찾은 경우
			if (current == -1) return -1;
			
			// 방문처리
			visited[current] = true;
			
			// 선택 정점이 도착정점이면 바로 빠져나가기
			if (current == end) return distance[end];
			
			// 2단계 : 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고
			// 기존 최적해보다 유리하면 갱신
			for (Vertex temp = adjList[current]; temp != null; temp = temp.link) {
				if (!visited[temp.vertex] &&
					distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		}
		if (distance[end]== Integer.MAX_VALUE) return -1;
		else return distance[end];
	}
}

/*
 * 1 -> v1 -> v2 -> N
 * 1 -> v2 -> v1 -> N
 * 두가지 경우의 수로
 * 계산해서 더 짧은거 출력.
 * 
 */
