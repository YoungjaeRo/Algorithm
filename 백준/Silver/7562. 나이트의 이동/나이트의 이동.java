import java.io.*;
import java.util.*;

public class Main {
	static int T;

	static int l; // 체스판의 길이

	static int[][] dist; // dist[r][c] = (r,c)까지 최소 이동 횟수
	static boolean[][] visited;

	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

	static class Node{
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			l = Integer.parseInt(br.readLine());

			dist = new int[l][l]; // 이동 횟수
			visited = new boolean[l][l]; // 방문 체크

			// 현재 나이트가 있는 칸
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 나이트가 이동하려는 곳
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());

			// BFS 시작
			ArrayDeque<Node> q = new ArrayDeque<>();
			visited[x][y] = true;
			q.offer(new Node(x, y));

			boolean found = false;

			while(!q.isEmpty() && !found) {
				Node cur = q.poll();

				int cx = cur.x;
				int cy = cur.y;

				for(int d = 0; d < 8; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					if(nx < 0 || nx >= l || ny < 0 || ny >= l) {
						continue;
					}

					if(visited[nx][ny]) {
						continue;
					}

					// 방문처리
					visited[nx][ny] = true;

					// 값은 도착 여부를 떠나서 꼭 업데이트해야 정확한 이동 횟수가 나온다
					dist[nx][ny] = dist[cx][cy] + 1;

					// 도착점에 도달했다면, 종료 (큐에 더 넣을 필요가 없음)
					if(nx == fx && ny == fy) {
						found = true;
						break;
					}
					q.offer(new Node(nx, ny));
				}
			}

			sb.append(dist[fx][fy]).append('\n');
		}

		System.out.println(sb.toString());
	}
}
