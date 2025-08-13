import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class S_1226_미로1_DFS {
         
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
             
            // dfs 시작
            boolean result = dfs(startX, startY);
             
            sb.append(result ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
     
    private static boolean dfs(int x, int y) {
        // 현재 위치 방문 처리
        visited[x][y] = true;
 
        // 기저부분
        // 도착점에 도달했는지 확인
        if (maze[x][y] == 3) return true;
 
        // 유도부분
        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            // 다음 위치가 미로 범위 내에 있고, 벽이 아니며, 방문하지 않은 경우
            if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && // 미로 범위 내
                maze[nx][ny] != 1 && !visited[nx][ny]) { // 벽아니고 && 방문하지않음
                 
                // 위의 if문 조건성립 -> 방문해야할곳 -> 재귀 호출
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }
        // 모든곳을 탐색했으나 도착점에 도달하지 못한 경우
        return false;
    }
 
}