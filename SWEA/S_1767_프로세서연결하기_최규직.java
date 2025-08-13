import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class S_1767_프로세서연결하기 {
	
	static class Core {
        int r, c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


	private static int N, maxCore, minLength;
	private static int[][] map;
    private static List<Core> cores;
    private static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우 순서임.
    private static int[] dc = {0, 0, -1, 1};

	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine()); // TC 개수
        
        // T개의 TC 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");

            N = Integer.parseInt(in.readLine());
            
            // 전역변수들 초기화
            cores = new ArrayList<>();
            maxCore = 0;
            minLength = Integer.MAX_VALUE;
    		
    		map = new int[N][N];
    		// 맵에 입력하면서 탐색해야할 코어 위치도 리스트에 따로 저장.
    		for (int i = 0; i < N; i++) {
    			String[] temp = in.readLine().split(" ");
            	for (int j = 0; j < temp.length; j++) {
					map[i][j] = Integer.parseInt(temp[j]); // 맵에 입력
                    if (map[i][j] == 1) { // (i,j) 위치가 코어이고
                        // 가장자리라면
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                            maxCore++; // 미리 연결된 코어 수에 포함
                        } else {
                            // 가장자리가 아닌 코어는 리스트에 추가
                            cores.add(new Core(i, j));
                        }
					}
				}
    		
    		
            // 백트래킹 시작
            dfs(0, maxCore, 0);
    		}
    	
            sb.append(minLength + "\n");
        }
        System.out.println(sb);
}

	


	private static void dfs(int idx, int coreCnt, int wireLen) {
		// 기저 조건: 모든 코어를 다 탐색했을 경우
        if (idx == cores.size()) {
            // 현재 연결된 코어 수가 최대 코어 수보다 많으면 갱신
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minLength = wireLen;
            } 
            // 연결된 코어 수가 같으면, 전선 길이의 최솟값만 갱신
            else if (coreCnt == maxCore) {
                minLength = Math.min(minLength, wireLen);
            }
            return;
        }
        
        // 현재 탐색할 코어의 위치를 가져옴
        Core current = cores.get(idx);

        // 4방향 연결시도
        for (int d = 0; d < 4; d++) {
            // 전선 연결이 가능하면 길이를 반환, 불가능하면 0 반환
            int length = checkAndConnect(current.r, current.c, d);

            if (length > 0) { // 연결이 가능하면
                // 다음 코어 재귀호출
                dfs(idx + 1, coreCnt + 1, wireLen + length);
                // 백트래킹을 위해 방금 놓았던 전선을 다시 제거
                disconnect(current.r, current.c, d, length);
            }
        }
        
        // 현재코어에서 연결이 안되면 다음코어로 넘어감
        // 코어 수와 전선 길이는 그대로 유지
        dfs(idx + 1, coreCnt, wireLen);
    }


	private static int checkAndConnect(int r, int c, int d) {
		int len = 0; // 전선길이
        int nr = r + dr[d];
        int nc = c + dc[d];
        

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 벽 만날때까지 진행
            if (map[nr][nc] != 0) { // 다른 코어나 전선이 있으면 연결 불가
                return 0; 
            }
            nr += dr[d];
            nc += dc[d];
            len++;
        }
        
        // while문을 빠져나왔다는 것은
        // 전선이 놓일 수 있는 경로가 있다는 뜻이므로
        // 맵에 전선 표시 -> 아래 disconnect 메서드에서 동일. 2표시 <-> 0표시
        nr = r + dr[d];
        nc = c + dc[d];
        for (int i = 0; i < len; i++) {
            map[nr][nc] = 2; // 전선은 2로 표시
            nr += dr[d];
            nc += dc[d];
        }
        
        return len;
	}

	
	private static void disconnect(int r, int c, int d, int length) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        for (int i = 0; i < length; i++) {
            map[nr][nc] = 0; // 0으로 복구
            nr += dr[d];
            nc += dc[d];
        }
	}
	
}



/* 
 * "최대한" 많이 core에 전원을 연결.
 * -> 4방향이 core에 막혀있다면 전선 연결할 수 없다고 판단하면 됨.
 * 모서리에 인접한 core는 전선 연결이 필요 없음. -> 신경쓰지말것
 * 백트래킹으로 전선 연결해보면서 전선의 최소값 찾기.
 * max 코어연결수 中 전선의 최소값을 찾아야 함.
 */