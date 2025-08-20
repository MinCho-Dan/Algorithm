package tests;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class S_14510_나무높이 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for (int q = 1; q <= T; q++) {
            sb.append("#" + q + " ");

            int N = Integer.parseInt(in.readLine());
            int[] tree = new int[N];
            String[] temp = in.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(temp[i]);
            }

            // 1. 최대 높이와 전체 높이 차이 계산
            int max_h = 0;
            for (int h : tree) {
                if (h > max_h) {
                    max_h = h;
                }
            }
            
            int total_diff = 0;
            int odd_diff_count = 0;
            for (int h : tree) {
                int diff = max_h - h;
                total_diff += diff;
                if (diff % 2 != 0) {
                    odd_diff_count++;
                }
            }

            // 2. 일수를 1일씩 늘려가며 최소 일수 탐색
            int days = 0;
            while (true) {
            	if (days==0 && total_diff==0) { // 처음부터 전체 높이가 같다면
            		sb.append(days + "\n"); // 0을 출력.
                	break;
            	}
 
                days++;
                int odd_days_possible = (days + 1) / 2; // 홀수일 횟수
                int even_days_possible = days / 2;     // 짝수일 횟수

                // 조건 : 홀수일 횟수가 홀수 차이 개수보다 많거나 같아야 함
                // 홀수 차이를 가진 나무는 반드시 홀수일에 물을 줘야함
                if (odd_days_possible < odd_diff_count) {
                    continue;
                }
                
                // 조건 : 총 증가량이 모든 나무의 차이를 충당해야 함
                // 홀수일에는 1씩, 짝수일에는 2씩 증가하므로 총 증가량은 (홀수일*1 + 짝수일*2)
                int total_increments_possible = odd_days_possible + 2 * even_days_possible;
                if (total_increments_possible >= total_diff) {
                    // 두 조건을 모두 만족하는 첫 번째 days를 출력.
                    sb.append(days + "\n");
                    break;
                }
                
            }
        }
        System.out.println(sb);
    }
}