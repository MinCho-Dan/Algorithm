import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_1861_정사각형방 {

	private static int N;
	private static int[][] room; // 방 배열
	private static int[][] save; // 해당 위치 방에서 갈 수 있는 최대경로수 저장
    private static int[] dx = {-1, 1, 0, 0}; // 상하좌우 순서로
    private static int[] dy = {0, 0, -1, 1}; // 상하좌우 순서로
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine()); // TC 개수
         
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
             
            N = Integer.parseInt(in.readLine());
            
            room = new int[N][N]; // 방 배열 만들기
            save = new int[N][N]; // 최대경로수 저장 배열 만들기
            for (int i = 0; i < N; i++) {
                String[] line = in.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(line[j]);
                }
            }
             
            int maxCount = 0;
            int startRoom = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = countMove(i, j); // 이중 for문으로 모든 방에서 탐색 시작
                    
                    if (count > maxCount) {
                        maxCount = count; // 최대 이동 값과
                        startRoom = room[i][j]; // 시작점을 저장
                    } else if (count == maxCount) { // 최대이동값이 동일하다면 
                        startRoom = Math.min(startRoom, room[i][j]); // 더 작은 방 번호 선택
                    }
                }
            }
            sb.append(startRoom + " " + maxCount + "\n");
        }
        System.out.println(sb);
    }
     
    private static int countMove(int x, int y) {
    	if (save[x][y] != 0) return save[x][y]; // 메모이제이션 활용

    	int max = 1; // 자기 자신도 포함해서 기본값 1로.
    	
		// 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 다음 위치가 범위 내에 있고, 갈 수 있는 경우
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && // 행렬 범위 내
                room[nx][ny] == room[x][y] + 1) { // 갈수있음
                // 위의 if문 조건성립 -> 방문해야할곳 -> 재귀 호출
            	max = Math.max(max, 1+countMove(nx, ny));
            }
        }
        
        save[x][y] = max; // 계산된 값 저장
        return max;
    }
 
}


/*
 * N * N의 행렬에서 상하좌우 인접한 위치가 +1값이면 이동 가능.
 * 그렇게 이동해서 최대한 많이 움직이는 경우의 시작점의 값.
 * 많이 움직이는 경우가 여럿이라면 그중에 시작점의 숫자가 가장 작은 값 출력.
 * DFS 완전탐색으로 풀면 될거 같은데?
 * 최적화를 조금 더 한다면 메모이제이션 활용하기?
*/
