import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class S_1224_계산기3 {
	
	private static int N;
	private static Deque<String> stack = new ArrayDeque<>();
 
	public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
            
            		
            N = Integer.parseInt(in.readLine());
            
            String[] tmp = in.readLine().split("");
            StringBuilder temp = new StringBuilder();
            
            // 후위계산식으로 변경하기
            for (int i = 0; i < N; i++) {
            	if (tmp[i].equals("(")||
        			tmp[i].equals("*")||
        			tmp[i].equals("/")) { // 무조건 push하는 애들
            		stack.push(tmp[i]);
            	}
            	else if (tmp[i].equals("+")|| // 덧셈, 뺄셈일때
                	tmp[i].equals("-")) {
	            		if (!stack.isEmpty()) {
	            			while (!stack.isEmpty() &&
	            					(stack.peek().equals("/") || // 곱,나눔이면 빼고
	        	        			stack.peek().equals("*")||
	        	        			stack.peek().equals("+")||
	        	        			stack.peek().equals("-"))) {
	            				temp.append(stack.pop());
	            			}
	            			stack.push(tmp[i]);
	            		}
	            		else {
	            			stack.push(tmp[i]);
	            		}
            	}
            	else if (tmp[i].equals(")")) { // 닫는 괄호일 때
                	while (!stack.peek().equals("(")) { // 여는 괄호 만날때까지 뺀다
                		temp.append(stack.pop());
                	}
                	stack.pop();
            	}
            	else {
            		temp.append(tmp[i]);
            	}
            }
            while (!stack.isEmpty()){temp.append(stack.pop());}
            
//            System.out.println("후위식은 : " + temp);
            
            // 후위계산식을 계산하기
            String[] tmp2 = temp.toString().split("");
            StringBuilder temp2 = new StringBuilder();
            int w = -1;
            for (int i = 0; i < temp.length(); i++) {
            	if (tmp2[i].equals("/")) {stack.push(Integer.toString(cal(1)));}
            	else if (tmp2[i].equals("*")) {stack.push(Integer.toString(cal(2)));}
            	else if (tmp2[i].equals("+")) {stack.push(Integer.toString(cal(3)));}
            	else if (tmp2[i].equals("-")) {stack.push(Integer.toString(cal(4)));}
        		else {stack.push(tmp2[i]);}
            	}
            temp2.append(stack.pop());
//            System.out.println("계산한 값은 : " + temp2);
            sb.append(temp2+"\n");
            }

            
      System.out.println(sb);
	}


	
	private static int cal(int w) {
		
		int a = Integer.parseInt(stack.pop());
		int b = Integer.parseInt(stack.pop());
		
		if (w == 1) {return a/b;}
		else if (w == 2) {return a*b;}
		else if (w == 3) {return a+b;}
		else {return a-b;}
	}
	
}



/*
 * 스택을 활용해서 중위표기식을 후위표기식으로 바꾸고 
 * 계산하는 프로그램을 만들기.
 * 
 * 
 * 
 * 
*/
