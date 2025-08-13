import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class M_1697_숨바꼭질 {
	

	private static int N;
	private static int K;
	private static int[] visited;
	static int sec;

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		// 0초인 경우
		if (N == K) {
            System.out.println(0);
            return;
        }
		
		visited = new int[100001];

		sol(N,K,0);
	}

	private static void sol(int n, int k, int min) {
		
		Queue<Integer> queue = new ArrayDeque<>(); // 큐 선언
		
		queue.offer(n);
		visited[n] = 1;
		
		int current = n;

		while (!queue.isEmpty()) {
			
			// 방문예약 한 것 중 맨 앞에 있는 정점
			current = queue.poll();
			
			// 세 가지 이동 경로
			int[] moves = {current - 1, current + 1, current * 2};
            
            for (int next : moves) {
                // 범위 체크
                if (next < 0 || next > 100000) {continue;}
                
                // 아직 방문하지 않았다면
                if (visited[next] == 0) {
                    visited[next] = visited[current] + 1; // 시간 업데이트
                    queue.offer(next);
                    
                    // 동생을 찾았다면 종료
                    if (next == K) {
                        System.out.println(visited[K] - 1); // 1초 부터 시작했으므로 -1
                        return;
                    }
                }
            }
        }
    }
}

/*
 * 예전에 파이썬으로 비슷한 문제 풀어봤던거 같은데.. 어떻게 했는지 기억 안남
 * 얘도 BFS로 푸는거 같은데
 * BFS의 while문 안에서 3가지 이동방법 모두 고려해서 큐에넣고뺴고...
 * visited 배열을 0=방문하지 않음 1부터는 시간초로 생각해서 계산.
 * 단, 초기방문을 1로 잡아줘야했어서 마지막에 출력할때 -1 하는거 까먹지 말고 하기.
*/
