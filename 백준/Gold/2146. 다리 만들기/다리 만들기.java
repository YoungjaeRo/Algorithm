import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;   // 0: 바다, 1: 육지(입력), 라벨링 후 2,3,4..: 섬 번호
	static int[][] owner; // 바다(또는 시작점 테두리 육지)가 어느 섬에서 확장됐는지
	static int[][] dist;  // 그 섬에서 해당 칸까지 바다를 몇 칸 건넜는지

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	static class Node {
		int x, y;
		Node(int x, int y) { this.x = x; this.y = y; }
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		owner = new int[N][N];
		dist = new int[N][N];

		// 1) 섬 라벨링 후, "테두리 육지"를 넣어둘 큐 (멀티소스 시작점)
		Queue<Node> startQ = new ArrayDeque<>();

		boolean[][] visited = new boolean[N][N];
		int islandId = 2; // 섬 번호 2부터

		// 섬 라벨링 + 테두리 수집
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					labelIslandAndCollectBorders(i, j, islandId, visited, startQ);
					islandId++;
				}
			}
		}

		// 2) 멀티소스 BFS로 최소 다리 길이 구하기
		int ans = multiSourceBfs(startQ);

		System.out.println(ans);
	}

	static void labelIslandAndCollectBorders(int x, int y, int islandId,
		boolean[][] visited, Queue<Node> startQ) {

		Queue<Node> q = new ArrayDeque<>();

		visited[x][y] = true;
		map[x][y] = islandId;
		q.offer(new Node(x, y));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			boolean isBorder = false; // 바다와 닿았는지

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

				// 주변에 바다(0)가 하나라도 있으면 현재 육지는 테두리
				if (map[nx][ny] == 0) isBorder = true;

				// 아직 라벨링 안 된 육지(1)면 같은 섬이므로 확장
				if (map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny] = islandId;
					q.offer(new Node(nx, ny));
				}
			}

			// 테두리 육지는 멀티소스 BFS 시작점으로 큐에 넣음 (한 번만)
			if (isBorder) {
				startQ.offer(new Node(cur.x, cur.y));

				// 이 시작점(육지)도 "어느 섬 소속인지" owner에 기록해둬야
				// 멀티소스 BFS에서 myOwner를 꺼낼 수 있음
				owner[cur.x][cur.y] = islandId;

				// 테두리 육지에서 출발 -> 바다를 건넌 칸 수는 0
				dist[cur.x][cur.y] = 0;
			}
		}
	}

	// 멀티소스 BFS
	static int multiSourceBfs(Queue<Node> q) {
		int ans = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			int myOwner = owner[cur.x][cur.y];

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

				// 1) 다음 칸이 바다(0)인 경우
				if (map[nx][ny] == 0) {

					// (1) 아직 아무 섬도 점령 안 한 바다면 -> 내가 점령
					if (owner[nx][ny] == 0) {
						owner[nx][ny] = myOwner;                  // 이 바다는 내 섬이 확장해왔다
						dist[nx][ny] = dist[cur.x][cur.y] + 1;    // 바다 1칸 더 건넘
						q.offer(new Node(nx, ny));                // 계속 확장
					}
					// (2) 이미 점령된 바다인데, 점령한 섬이 나랑 다르면 -> 바다-바다 충돌
					else if (owner[nx][ny] != myOwner) {
						// cur + nx
						int candidate = dist[cur.x][cur.y] + dist[nx][ny];
						ans = Math.min(ans, candidate);
					}
				}

				// 2) 다음 칸이 육지(섬 번호)인 경우

				else {
					// map[nx][ny]는 섬 번호(2,3,4...)일 것
					int otherIsland = map[nx][ny];

					// 다른 섬의 육지에 닿았다면 -> 현재까지 건넌 바다 칸 수(dist[cur])가 답 후보
					if (otherIsland != myOwner) {

						ans = Math.min(ans, dist[cur.x][cur.y]);
						
					}
				}
			}
		}

		return ans;
	}
}