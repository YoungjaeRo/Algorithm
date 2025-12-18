import java.io.*;
import java.util.*;

public class Main {

    /**
     * Node 하나로 통일
     * - 그래프에서는: (다음 도시, 해당 간선 비용)
     * - PQ에서는: (현재 도시, 시작점부터 누적 비용)
     */
    static class Node implements Comparable<Node> {
        int v;      // 도시 번호
        int cost;   // 비용 (그래프: 간선 비용 / PQ: 누적 비용)

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        // 우선순위큐에서 비용이 작은 게 먼저 나오도록
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시 수
        int N = Integer.parseInt(br.readLine().trim());
        // 버스 수 (간선 수)
        int M = Integer.parseInt(br.readLine().trim());

        // 인접 리스트
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        /**
         * 간선 입력
         * from -> to 로 cost 비용의 버스
         */
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 그래프에서는 "간선 정보" 의미
            graph[from].add(new Node(to, cost));
        }

        // 시작 도시, 도착 도시
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end   = Integer.parseInt(st.nextToken());

        // 최단거리 배열
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        // 시작점 초기화
        dist[start] = 0;

        // 다익스트라용 우선순위큐
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // PQ에서는 "누적 비용" 의미
        pq.add(new Node(start, 0));

        /**
         * 다익스트라 시작
         */
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int curCity = cur.v;
            int curCost = cur.cost;

            /**
             * 핵심 스킵 로직
             * 이미 더 짧은 거리로 갱신된 적이 있으면
             * 지금 꺼낸 값은 의미 없음
             */
            if (curCost > dist[curCity]) {
                continue;
            }

            // 현재 도시에서 갈 수 있는 모든 도시 탐색
            for (Node next : graph[curCity]) {
                int nextCity = next.v;
                int nextCost = curCost + next.cost;

                /**
                 * 더 짧은 경로 발견 시 갱신
                 */
                if (nextCost < dist[nextCity]) {
                    dist[nextCity] = nextCost;

                    // PQ에는 누적 비용으로 넣는다
                    pq.add(new Node(nextCity, nextCost));
                }
            }
        }

        // 시작점 -> 도착점 최소 비용 출력
        System.out.println(dist[end]);
    }
}
