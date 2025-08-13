import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class S_1238_Contact {

	private static int N; // 길이
	private static int start; // 시작점
	private static int[][] adjMatrix; // 인접행렬
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

         
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
             
            String[] split = in.readLine().split(" ");
            N = Integer.parseInt(split[0]);
            start = Integer.parseInt(split[1]);
            
            adjMatrix = new int[101][101];
            
            String[] tmp = in.readLine().split(" ");
            int[] tmp2 = new int[N];
            for (int i = 0; i < N; i++) {
            	tmp2[i] = Integer.parseInt(tmp[i]);
            }
            for (int i = 0; i < N; i=i+2) {
				adjMatrix[tmp2[i]][tmp2[i+1]] = 1;
			}
            int[] depth = bfs(start);
            
            // 최대깊이 찾기
            int maxDepth = 0;
            int answer = 0;
            
            for (int i = 1; i <= 100; i++){
            	if (depth[i] > maxDepth) {
            		maxDepth = depth[i];
            	}
            }
            
            // 최대깊이의 정점 중 가장 번호가 큰 정점 찾기
            for (int i = 1; i <= 100; i++) {
            	if (depth[i] == maxDepth) {
                    if (i > answer) {
                        answer = i;
                    }
                }
            }

           
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
     
    private static int[] bfs(int start) {
		
		Queue<Integer> queue = new ArrayDeque<>(); // 큐 선언
		int[] depth = new int[101]; // 방문체크용 배열
		
		// 첫번째 정점 방문 예약
		queue.offer(start); 
		depth[start] = 1; // 시작 정점의 깊이는 1.
		
		int current = start; // current를 시작 정점으로 초기화.
		while (!queue.isEmpty()) {
			
			// 방문예약 한 것 중 맨 앞에 있는 정점
			current = queue.poll();
			
			// 방문한 정점에서 해야할 일 처리
			
			// for문을 이용해서 0번 정점부터 V-1번 정점까지 탐색
			for (int i = 0; i < 101; i++) {
				if (adjMatrix[current][i] != 0 && // 인접여부 확인
						depth[i]==0) { // 방문여부 체크
					// 다음 정점을 방문예약.
					queue.offer(i);
					depth[i] = depth[current] + 1; // depth 카운트
				}
				
			}

		}
		return depth;
	}
 
}


/*
 * BFS 사용.
 * depth를 세서 max depth인 정점들 중에 가장 큰 수를 return하면 될거 같은데.
 * 
*/
