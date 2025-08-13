import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class S_1219_길찾기 {
         
    private static int N;
    private static ArrayList<Integer>[] adjList;
    private static boolean[] visited;
    private static boolean found;
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        for (int test_case = 1; test_case <= 10; test_case++) { // 테케 10회
            sb.append("#" + test_case + " ");
             
            // 테스트 케이스 번호(tcNum)와 길의 총 개수(N)를 받음
            String[] NN = in.readLine().split(" ");
            N = Integer.parseInt(NN[1]); // N만 사용할거임
 
            // adjList(인접리스트) 만들기
            adjList = new ArrayList[100];
            for (int i = 0; i < 100; i++) {
                adjList[i] = new ArrayList<>();
            }
 
            // 길 정보 입력
            String[] pathInfo = in.readLine().split(" ");
            for (int i = 0; i < N * 2; i += 2) {
                int from = Integer.parseInt(pathInfo[i]);
                int to = Integer.parseInt(pathInfo[i + 1]);
                adjList[from].add(to); // index가 출발점이고 값이 도착점임
            }
 
            // DFS를 위한 초기화
            visited = new boolean[100];
            found = false;
 
            dfs(0);
 
            sb.append(found ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void dfs(int current) {
        // 가지치기 : 이미 99번에 도착한 경우 더 이상 탐색하지 않음
        if (found) {return;}
 
        // 현재 지점 방문 처리
        visited[current] = true;
         
        //기저부분
        // 99번 지점에 도착했는지 확인
        if (current == 99) {
            found = true;
            return;
        }
 
        // 유도부분
        // 현재 지점에서 갈 수 있는 다음 지점들을 탐색
        for (int next : adjList[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}