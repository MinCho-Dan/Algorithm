import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_2961_도영이가만든맛있는음식 {

    private static int N;
    private static int[][] ingredients;
    private static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        ingredients = new int[N][2]; // [신맛, 쓴맛] 이중배열로 넣기

        for (int i = 0; i < N; i++) {
            String[] split = in.readLine().split(" ");
            ingredients[i][0] = Integer.parseInt(split[0]); // 신맛
            ingredients[i][1] = Integer.parseInt(split[1]); // 쓴맛
        }
        
        findMinDiff(0, 1, 0);

        System.out.println(minDiff);
    }

    private static void findMinDiff(int cnt, int sour, int bitter) {
        
        // 기저 조건: 모든 재료를 고려했을 때(cnt==N) return
        if (cnt == N) {
            if (bitter != 0) { // 재료를 하나도 선택하지 않은 경우는 제외
                int diff = Math.abs(sour - bitter);
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
            return;
        }

        // 유도 조건
        // 현재 재료를 선택하는 경우
        findMinDiff(cnt + 1, sour * ingredients[cnt][0], bitter + ingredients[cnt][1]);

        // 현재 재료를 선택하지 않는 경우
        findMinDiff(cnt + 1, sour, bitter);
    }
}