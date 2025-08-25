import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;


public class B_14698_전생슬 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		long div = 1000000007; // 나누는 수
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(in.readLine()); // 슬라임 수
			
			if (N==1) { // 전기 에너지가 필요 없을 때
				sb.append(1 + "\n"); // 1출력하고
				in.readLine(); // 필요없는 슬라임 1개를 읽은 다음 
				continue; // 다음 테케로
			}
			
			Queue<Long> pQueue = new PriorityQueue<>(); // 우선순위 큐에
			String[] tmp = in.readLine().split(" "); // 입력값을 받아서
			for (String s : tmp) {
				if (!s.isEmpty()) pQueue.offer(Long.parseLong(s)); // 슬라임 넣어주기
			}
			
			long result = 1;
			while (pQueue.size() > 1) { // 슬라임이 한마리 남으면 종료
				long temp = (pQueue.poll() * pQueue.poll()); // 슬라임 * 슬라임
				pQueue.offer(temp); // 합성한 값 큐에 넣어주기
				result = result * (temp % div) % div; // 결과에 누적곱셈하고 div로 나눈 나머지
			}
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb);
	}

}
