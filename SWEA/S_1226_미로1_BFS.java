import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
 
public class S_1226_미로1_BFS {
         
    private static final int Size = 16;
    private static int[][] maze;
    private static int startX, startY;
    private static int[] dx = {-1, 1, 0, 0}; // 상하좌우 순서로
    private static int[] dy = {0, 0, -1, 1}; // 상하좌우 순서로
    private static boolean[][] visited;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        for (int test_case = 1; test_case <= 10; test_case++) { // 테케 10회
            sb.append("#" + test_case + " ");
             
            in.readLine(); // TC번호 버리기용
            maze = new int[Size][Size];
            visited = new boolean[Size][Size];
             
            // 미로 입력 및 시작점 찾기
            for (int i = 0; i < 16; i++) {
                String line = in.readLine(); // 한줄 읽어서
                for (int j = 0; j < 16; j++) { // maze[i]에 []로 집어넣기
                    maze[i][j] = Character.getNumericValue(line.charAt(j));
                    if (maze[i][j] == 2) { // 집어넣을때 시작점도 같이 검사
                        startX = i;
                        startY = j;
                    }
                }
            }

            
            boolean result = bfs(startX, startY);
             
            sb.append(result ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
     
    
    private static boolean bfs(int x, int y) {
		Queue<Integer> queue = new ArrayDeque<>(); // 큐 선언
		
		// 첫번째 정점 방문 예약
		queue.offer(x);
		queue.offer(y);
		visited[x][y] = true; // 방문 예약시 방문처리
		
		while (!queue.isEmpty()) {
			
			// 방문예약 한 것 중 맨 앞에 있는 정점
			x = queue.poll();
			y = queue.poll();
			
			// 방문한 정점에서 해야할 일 처리 -> 예시는 알파벳 출력.
			if (maze[x][y] == 3) return true;
			
			for (int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && // 미로 범위 내
	            		maze[nx][ny] != 1 && !visited[nx][ny]) { // 방문여부 체크
						
						// 다음 정점을 방문예약.
						queue.offer(nx);
						queue.offer(ny);
						visited[nx][ny] = true; // 첫정점과 마찬가지로 방문예약시 방문처리
				}
			}
		}
		return false;
	}
 
}