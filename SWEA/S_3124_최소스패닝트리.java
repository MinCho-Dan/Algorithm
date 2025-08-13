import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// Prim 알고리즘 버전

public class S_3124_최소스패닝트리 {

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
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine()); // TC 개수
        
        // T개의 TC 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");

            String[] tmp = in.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int E = Integer.parseInt(tmp[1]);
    		
    		Vertex[] adjList = new Vertex[N+1]; // 인접리스트
    		int[] minEdge = new int[N]; // 다른정점에서 자신으로 연결하는 간선비용 중 최소비용
    		boolean[] visited = new boolean[N];// 신장트리에 선택된 여부
    		Arrays.fill(minEdge, Integer.MAX_VALUE); // 무한대 값, 충분히 큰 값으로 초기화
    		
    		for (int i = 0; i < E; i++) {
    			String[] temp = in.readLine().split(" ");
            	int from = Integer.parseInt(temp[0])-1;
            	int to = Integer.parseInt(temp[1])-1;
            	int weight = Integer.parseInt(temp[2]);
    			adjList[from] = new Vertex(to, weight, adjList[from]);
    			adjList[to] = new Vertex(from, weight, adjList[to]);
    		}
    	
    		
    		// 0단계 : 첫 방문 정점의 최소비용을 0으로 설정
    		int vertexCount = 0; // 선택된 정점의 개수
    		long result = 0; // MST 비용
    		minEdge[0] = 0; // 처음에 0번 정점이 방문되도록 비용 0으로 설정
    		
    		
    		Queue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
    		pQueue.offer(new Vertex(0,  minEdge[0], null));
    		
    		// 모든 정점을 순회하기
    		while (!pQueue.isEmpty()) {
    			
    			
    			// 우선순위 큐
    			// 1단계 : 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점을 선택
    			Vertex current = pQueue.poll();
    			if(visited[current.vertex]) {
    				continue;
    			}

    			// 선택된 정점을 방문 (신장트리에 포함)
    			visited[current.vertex] = true;
    			
    			result += current.weight; // 비용 누적
    			
    			//(선택사항) 가지치기
    			if (++vertexCount == N) { // 최소신장트리가 완성되었다면
    				break;
    			}

    			// 2단계 : 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의
    			// 기존 최소비용과 비교해서 갱신
    			// 신장트리에 새롭게 추가되는 정점의 모든 인접정점 들여다보며 처리
    			for (Vertex temp = adjList[current.vertex]; temp != null; temp = temp.link) {
    				
    				// 신장트리에 포함되지 않았고, 
    				// 선택된 정점과 인접한 정점이며,
    				// 선택된 정점과의 비용이 이전까지의 최소비용보다 작다면
    				if (!visited[temp.vertex]&&
    					minEdge[temp.vertex] > temp.weight) {
    					
    					minEdge[temp.vertex] = temp.weight; // 최소비용으로 갱신
    					pQueue.offer((new Vertex(temp.vertex, minEdge[temp.vertex], null)));
    				}
    			}			
    		}
            sb.append(result + "\n");
        }
        System.out.println(sb);
	}

}
