import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class M_11286_절댓값힙 {
	
	
	public static void main(String[] args) throws Exception {
		 
        Scanner in = new Scanner(System.in);
     
        int N = in.nextInt();
        
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) { 
				int abs1 = Math.abs(o1);
				int abs2 = Math.abs(o2);
				
				if (abs1 != abs2) { // 절대값이 같지 않으면
					return abs1 - abs2; // 절대값이 작은 수 먼저
				}
				else return o1-o2; // 절대값이 같으면 원래 값이 작은 수 먼저
			}
		});
        
        for (int i = 0; i < N; i++) {
        	int tmp = in.nextInt();
        	if (tmp == 0) {
        		if (queue.isEmpty()) {
        			System.out.println(0);// 힙이 비었다면 0 출력
        		}
        		
        		else {
        			System.out.println(queue.poll()); // 아니라면 꺼내서 출력
        		}
        	}
        	else if (tmp !=0) queue.offer(tmp); // 0이아닌 정수라면 힙에 넣기
		}
        
        
       
	}
}


/*
 * 절대값이 작은거. 절대값이 같다면 원래값이 작은 값을.
 * 
*/
