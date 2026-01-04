import java.io.*;
import java.util.*;

public class Main {
    static int N; // 행(세로)
    static int M; // 열(가로)

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] map;
    static int[][] dist; // dist[r][c] = (r,c)까지 오는데 부순 벽 최소 개수

    static final int INF = 1_000_000_000;

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int cost; // 지금까지 부순 벽 개수(누적 비용)

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost); // cost 작은게 먼저 나오게
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        map = new int[N][M];
        dist = new int[N][M];

        // dist 초기화: 아직 최소값 모르면 INF로 깔아둔다
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        // 그래프 정보 입력받기
        // 0: 빈 방, 1: 벽
        for (int i = 0; i < N; i++) {          // <-- 여기 고침 (원래 i=N 이라 안 돌았음)
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 다익스트라 실행해서 정답 출력
        System.out.println(dijkstra());
    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작점 (0,0)
        // 시작은 벽을 부순 적이 없으니 비용 0
        dist[0][0] = 0;
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int r = cur.r;
            int c = cur.c;
            int cost = cur.cost;

            /**
             * 여기 매우 중요
             *
             * pq에는 같은 칸이 여러 번 들어갈 수 있다.
             * (더 좋은 cost로 갱신되면 또 넣음)
             *
             * 근데 pq에서 꺼낸 cost가 현재 dist[r][c]랑 다르면
             * 이건 "예전에 넣어둔 구버전"이다.
             * => 그냥 버려야 함.
             */
            if (cost != dist[r][c]) {
                continue;
            }

            // 목적지 도착하면 종료 가능
            // 다익스트라는 '최소 cost부터 꺼내기'라 여기서 꺼낸 cost가 곧 정답
            if (r == N - 1 && c == M - 1) {
                return cost;
            }

            // 상하좌우 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dx[dir];
                int nc = c + dy[dir];

                // 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                /**
                 * 다음 칸으로 이동할 때 드는 "추가 비용"
                 * - 빈 방(0) -> +0
                 * - 벽(1)   -> +1 (벽을 부수고 들어간다고 생각)
                 */
                int nextCost = cost + map[nr][nc];

                // 더 적은 벽을 부수고 갈 수 있으면 갱신
                if (nextCost < dist[nr][nc]) {
                    dist[nr][nc] = nextCost;
                    pq.add(new Node(nr, nc, nextCost));
                }
            }
        }

        // 이 문제는 항상 도달 가능하다고 보면 됨(그래도 안전하게)
        return dist[N - 1][M - 1];
    }
}
