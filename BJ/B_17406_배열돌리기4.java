package tests;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 모든 순서 고려해야한다 -> 순열
 * 배열 돌리는거 -> 빡구현
 * 깊은복사로 배열 복사해놓고 돌려야함.
 * 그리고 돌린 배열의 배열값 => 행의 합 중에 가장 작은 값.
 * 그냥 메서드로 각각 구현해놓고 레고조립 해야겠다
 * 순열, 배열 돌리기, 깊은복사, 최소값
 * 이렇게 메서드는 4개정도?
 */

class B_17406_배열돌리기4 {
	static int N, M, K;
    static int[][] initialArr;
    static int[][] operations;
    static boolean[] visited;
    static int[] order;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기배열
        initialArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                initialArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 문제에서는 1부터 시작하니까 편의상 0부터 시작하는걸로 수정
        operations = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            operations[i][0] = Integer.parseInt(st.nextToken()) - 1; // r
            operations[i][1] = Integer.parseInt(st.nextToken()) - 1; // c
            operations[i][2] = Integer.parseInt(st.nextToken());	 // s
        }

        // 순열용 배열 초기화
        visited = new boolean[K];
        order = new int[K];

        // 순열로 백트래킹
        permutation(0);

        System.out.println(minValue);
    }
    
    /**
     * 순열 메서드
     */
    private static void permutation(int depth) {
        // 기저조건
        if (depth == K) {
            doRotation(); // 해당 순서대로 배열을 회전시키는 함수 호출
            return;
        }

        // 유도조건
        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true; 
                order[depth] = i; 
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 순열 순서대로 배열 회전 및 최소값 구하는 메서드
     */
    private static void doRotation() { 
        // 원본 배열을 훼손하지 않기 위해 
    	// 깊은 복사 메서드 호출해서 복사
        int[][] currentArr = deepCopy(initialArr); 

        // 정해진 순서대로 회전 연산 적용
        // order 배열 -> 순열로 뽑은 하나의 경우의 수
        // for문으로 순서대로 회전시키기.
        for (int i = 0; i < K; i++) {
            int opIdx = order[i];
            int r = operations[opIdx][0];
            int c = operations[opIdx][1];
            int s = operations[opIdx][2];

            rotate(currentArr, r, c, s); // 회전 함수 호출
        }

        // 모든 회전이 끝난 후 배열의 행 합 중 최솟값 갱신
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += currentArr[i][j];
            }
            if (sum < minValue) {
                minValue = sum;
            }
        }
    }
    
	/**
	 * 깊은복사 메서드
	 */
    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        int[][] result = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
        	// arraycopy(원본배열, 원본시작위치, 대상배열, 대상시작위치, 복사길이)
        	// 블로그 검색해서 찾아옴 ㅎㅎ;
            System.arraycopy(original[i], 0, result[i], 0, original[0].length);
        }
        return result;
    }    
    
	/**
	 * 회전 메서드
	 */
    private static void rotate(int[][] arr, int r, int c, int s) {
    	// s개의 겹을 회전시킴 (가장 안쪽 겹부터 바깥으로)
        for (int i = 1; i <= s; i++) {
            int r1 = r - i, c1 = c - i; // 현재 겹의 좌상단 좌표
            int r2 = r + i, c2 = c + i; // 현재 겹의 우하단 좌표

            // 1. 위쪽 변을 오른쪽으로 이동
            // 원본 오른쪽 끝값(r1, c2)을 temp에 저장
            int temp = arr[r1][c2];
            for (int j = c2; j > c1; j--) {
                arr[r1][j] = arr[r1][j - 1];
            }

            // 2. 오른쪽 변을 아래로 이동
            // 원본 우측아래 끝값(r2, c2) temp2에 저장
            int temp2 = arr[r2][c2]; 
            for (int j = r2; j > r1; j--) {
                if (j == r1 + 1) { // (r1+1, c2) 위치에는 temp 값
                    arr[j][c2] = temp;
                } else {
                    arr[j][c2] = arr[j - 1][c2];
                }
            }
            
            // 3. 아래쪽 변을 왼쪽으로 이동
            // 원본 좌측아래 끝값(r2, c1) temp3에 저장
            int temp3 = arr[r2][c1]; // 왼쪽 변 회전을 위해 임시 저장
            for (int j = c1; j < c2; j++) {
                if (j == c2 - 1) { // (r2, c2-1) 위치에는 temp2 값
                    arr[r2][j] = temp2;
                } else {
                    arr[r2][j] = arr[r2][j + 1];
                }
            }

            // 4. 왼쪽 변을 위로 이동
            for (int j = r1; j < r2; j++) {
                if (j == r2 - 1) { // (r2-1, c1) 위치에는 temp3 값
                    arr[j][c1] = temp3;
                } else {
                    arr[j][c1] = arr[j + 1][c1];
                }
            }
        }
	}
    
	
}