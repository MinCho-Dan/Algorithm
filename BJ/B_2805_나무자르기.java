package tests;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2805_나무자르기 {

	public static void main(String[] args) throws Exception {
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
	    
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
	    StringTokenizer str = new StringTokenizer(in.readLine());
	    
	    int[] tree = new int[N];
	    int max = -1; // 주어진 나무들 중 최고 높은 나무
	    
	    for (int i = 0; i < N; i++) {
	    	tree[i] = Integer.parseInt(str.nextToken()); // 나무 높이 저장
	    	if (max < tree[i]) max = tree[i]; // 최고높이 max 변수에 저장
		}
	    
	    int result = 0;
	    int start = 0;
		
		while (start <= max) {
			int mid = (start + max) / 2;
			long totalWood = 0;
			
			for (int a : tree) {// mid 높이로 잘랐을때 얻는 나무의 총 길이
				if (a > mid) totalWood += a - mid;
			}
			
			
			if (totalWood >= M) { // M미터 이상을 얻었을 때
				result = mid; // mid는 답이 될  수 있고
				start = mid + 1; // mid보다 더 높은 높이 탐색
			}
			// M 미터 이상을 얻지 못했을 때,
			else max = mid - 1; // mid보다 낮은 높이 탐색
			
		}
	    
	    
		System.out.println(result);
        
    }


}

