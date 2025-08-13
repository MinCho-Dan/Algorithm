import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class S_1234_비밀번호 {
	
	private static int N;
	private static int[] num;
	private static Deque<Integer> stack = new ArrayDeque<>();
 
	public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
//        Integer.parseInt(in.readLine()); // TC 개수 버리기
         
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
             
            String[] split = in.readLine().split(" ");
            N = Integer.parseInt(split[0]);
            String tmp = split[1];
            num = new int[N];
            for (int i = 0; i < N; i++) {
            	num[i] = Character.getNumericValue(tmp.charAt(i));
            }
            
            // 스택에 넣을때 탑에 있는 숫자와 같다면 삭제
            for (int i = N-1; i >= 0 ; i--) { 
				if (!stack.isEmpty() && stack.peek() == num[i]) {stack.pop();}
				else stack.push(num[i]);
			}

            // 스택 사이즈만큼 반복문으로 sb에 append하기
            int cnt = stack.size();
            for (int i = 0; i < cnt; i++) {
            	sb.append(stack.pop());
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }
}

/*
 * 스택에 넣기전에 탑에 있는 숫자가 같은숫자면 pop해서 삭제.
 * input값 전부를 for문으로 끝까지 돌리고
 * 마지막에 스택안에 숫자들 꺼내서 출력하면 될듯
*/
