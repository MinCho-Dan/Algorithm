package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 지름길(간선) 정보를 저장하는 클래스
class Edge implements Comparable<Edge> {
    int end;
    int weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) { // 오름차순
        return this.weight - o.weight;
    }
}

public class B_1446_지름길 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        // 그래프를 인접 리스트로 표현
        // 0부터 D까지의 빈 공간을 작성
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        // 일반도로 간선 추가.
        // i=0이라면, 0번째 노드에 end=1, weight=1 내용을 저장.
        // i=1이라면, 1번째 노드에 end=2, weight=2 내용을 저장.
        for (int i = 0; i < D; i++) {
            graph.get(i).add(new Edge(i + 1, 1));
        }

        // 지름길 간선 추가
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            // 지름길이 D를 벗어나지 않고, 일반도로보다 더 짧을 때만 추가
            if (end <= D && end - start > len) {
                graph.get(start).add(new Edge(end, len));
            }
        }

        // 다익스트라 알고리즘
        int[] dist = new int[D + 1]; // 0부터 D까지 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // INF값으로 채우기
        dist[0] = 0; // 시작점 0으로 처리

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0)); // 시작점 큐에 넣기

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int end = current.end; // 도착점
            int curDist = current.weight; // 인접노드까지의 가중치

            // 현재 노드까지 거리가 이미 더 짧다면, 이 경로는 건너뜀
            if (dist[end] < curDist) { 
                continue;
            }

            // 현재 노드와 연결된 모든 인접 노드들을 탐색
            for (Edge neighbor : graph.get(end)) {
            	// 현재 노드를 거쳐서 인접 노드로 가는 새로운 경로의 거리 계산
            	int newDist = curDist + neighbor.weight;
            	
            	// 새로운 경로가 기존에 알려진 인접 노드까지의 최단 거리보다 짧다면,
                if (dist[neighbor.end] > newDist) {
                    dist[neighbor.end] = newDist; // 갱신
                    pq.add(new Edge(neighbor.end, newDist)); // 인접노드를 큐에 넣기
                }
            }
        }

        System.out.println(dist[D]);
    }
}

/*
 * 고속도로를 가는데, 고속도로 안에 지름길이 있다.
 * 입력에서 주는건 지름길만.
 * 그럼 1씩 증가하는 평범한 고속도로 노드들도 만들어야겠지?
 * 그리고 다익스트라 알고리즘으로 최단거리를 뽑으면...
 * 완....료.....
 * 
 */
