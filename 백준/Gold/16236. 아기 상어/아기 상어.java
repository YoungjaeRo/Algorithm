import java.util.*;
import java.io.*;

/**
 * 거리 탐색을 하는 문제이기 때문에 BFS를 사용
 * 같은 거리인데, 추가적인 조건이 있다? 이럴땐 우선순위 BFS 이므로, PriorityQueue를 사용해야한다.
 */
public class Main {
	static int N;
	static int[][] map; // 물고기, 벽이 저장돼있는 맵

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static int sharkSize = 2, eatCount = 0, time = 0;
	static int sharkX, sharkY;

	// Node 클래스 : 상어의 위치 + 거리
	static class Node implements Comparable<Node> {
		int x, y;
		int dist;

		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			if (this.dist != o.dist) {
				return this.dist - o.dist;         // 거리 기준 (오름차순)
			} else if (this.x != o.x) {
				return this.x - o.x;               // 위쪽이 우선 (행이 작을수록)
			} else {
				return this.y - o.y;               // 왼쪽이 우선 (열이 작을수록)
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

		//입력 처리 및 상어 위치 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					sharkX = i;
					sharkY = j;
					map[i][j] = 0; // 상어가 있던 자리는 빈칸 처리
				}
			}
		}

		//  반복 : 더 이상 먹을 수 있는 물고기가 없을때까지
		while (true) {
			int moveTime = bfs(); // 가장 가까운 물고기까지 가는 시간

			if (moveTime == -1) { // 더이상 먹을 수 잇는 물고기가 없음
				break;
			}

			time = time + moveTime; // 이동 시간 누적

		}

		System.out.println(time);
	}

	static int bfs() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(sharkX, sharkY, 0));
		visited[sharkX][sharkY] = true;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			// 먹을 수 있는 물고기 발견
			if (map[curr.x][curr.y] != 0 && map[curr.x][curr.y] < sharkSize) {
				eatCount++;
				map[curr.x][curr.y] = 0; // 물고기 먹음
				sharkX = curr.x;
				sharkY = curr.y;

				if (eatCount == sharkSize) {
					sharkSize++; // 자기 크기 만큼 먹었으면 상어 사이즈 업, 먹은 횟수 초기화
					eatCount = 0;
				}

				return curr.dist;
			}

			// 이동방향 탐색
			for (int dir = 0; dir < 4; dir++) {
				int nx = curr.x + dx[dir]; // 새로운 좌표
				int ny = curr.y + dy[dir]; // 22..

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue; // map 외의 범위면 continue 처리
				}
				if (visited[nx][ny]) { // 방문한 곳 이면, continue
					continue;
				}

				if (map[nx][ny] > sharkSize) {
					continue; // 더 큰 물고기면 못감
				}

				visited[nx][ny] = true;
				pq.offer(new Node(nx, ny, curr.dist + 1));
			}
		}

		return -1;
	}
}
