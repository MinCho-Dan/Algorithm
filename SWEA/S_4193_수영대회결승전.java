import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class S_4193_수영대회결승전 {
	
	private static class Point {
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

	private static int N; // 수영장 크기
	private static int[][] sea; // 수영장 정보 (0: 물, 1: 장애물, 2: 소용돌이)
	private static int startX, startY, endX, endY; // 시작점과 도착점 좌표
    private static int[] dx = {-1, 1, 0, 0}; // 상하 이동 (행 변화)
    private static int[] dy = {0, 0, -1, 1}; // 좌우 이동 (열 변화)
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
         
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            
            N = Integer.parseInt(in.readLine());
            
            sea = new int[N][N];
            for (int i = 0; i < N; i++) {
            	String[] tmp = in.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                	sea[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            
            String[] tmp = in.readLine().split(" ");
            startX = Integer.parseInt(tmp[0]);
            startY = Integer.parseInt(tmp[1]);
            
            tmp = in.readLine().split(" ");
            endX = Integer.parseInt(tmp[0]);
            endY = Integer.parseInt(tmp[1]);
            

            int result = bfs();

           
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
     
    private static int bfs() {
        // 방문 배열: visited[x좌표(행)][y좌표(열)][시간 % 3]
        boolean[][][] visited = new boolean[N][N][3];
        Queue<Point> queue = new ArrayDeque<>();

        // 시작점 큐에 추가 및 방문 처리
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;
            int time = current.time;

            // 도착점에 도달하면 현재 시간 반환
            if (x == endX && y == endY) {
                return time;
            }

            int nextTime = time + 1;
            int nextTimeMod = nextTime % 3;

            // 1. 상하좌우 이동 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 다음 행
                int ny = y + dy[i]; // 다음 열

                // 경계 체크
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                // 장애물 체크 (map[행][열] -> map[nx][ny])
                if (sea[nx][ny] == 1) continue;
                // 방문 체크
                if (visited[nx][ny][nextTimeMod]) continue;

                // 이동할 곳이 소용돌이인 경우
                if (sea[nx][ny] == 2) {
                    // 소용돌이는 2초 유지, 1초 잠잠. 즉, time % 3 == 2 일 때 잠잠.
                    // 현재 시간(time)을 기준으로 다음 시간(nextTime)에 소용돌이가 잠잠해야 통과 가능.
                    if (time % 3 != 2) {
                        continue; // 소용돌이가 활성화 상태이므로 통과 불가
                    }
                }
                
                // 모든 조건을 통과하면 큐에 추가 및 방문 처리
                visited[nx][ny][nextTimeMod] = true;
                queue.offer(new Point(nx, ny, nextTime));
            }

            // 2. 제자리에서 기다리기
            // 시작점이 아니라면 제자리에서 기다려서 다음 시간을 모색
            if (!visited[x][y][nextTimeMod]) {
                visited[x][y][nextTimeMod] = true;
                queue.offer(new Point(x, y, nextTime));
            }
        }

        // 도착점에 도달할 수 없는 경우
        return -1;
    }
}


/*
 * BFS 사용.
 * 큐에 {x, y, time} 형태로 저장해서 time이 min인값을 return 하면 될것 같다.
 * 소용돌이쪽 처리가 어려울거 같은데.
 * 소용돌이(2)는 0초,1초(못지나감),2초(지나감) 반복하니까 시간%3=2일때 지나갈 수 있음.
 * 시간 % 3 했을때 2
 * 장애물(1)은 그냥 못가는곳으로 보면 되고..
 * 테케 15개 중에 9개 PASS...
 * -> 원래 소용돌이를 만났을때, 시간에따라 대기값을 계산했었는데
 * -> 그냥 4방향 이동 로직을 for문으로 짜고, 추가로 대기해보는 경우의 수도 추가 했음.
 * 4방향 + 기다리기 모두를 수행하도록 DFS를 짜니까 PASS하는거 같다.
*/
