import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;

	//  동 서 남 북
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		visited = new boolean[N][N];

		// 지도에 집이 있는곳을 표시하기
		for(int i = 0; i < N; i++) {
			String line = br.readLine().trim(); // 앞뒤 공백 제거
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		// 오름차순 출력용
		List<Integer> sizes = new ArrayList<>();

		// 완탐 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					sizes.add(bfs(i, j)); // bfs에서 출력하는 영역의 넓이를 리스트에 저징
				}
			}
		}

		Collections.sort(sizes);
		StringBuilder sb = new StringBuilder();
		sb.append(sizes.size()).append("\n");
		for (int s : sizes) {
			sb.append(s).append("\n");
		}

		System.out.println(sb);
	}
	static int bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(i, j));
		visited[i][j] = true;

		int count = 1; // 탐색을 시작할때, 이미 영억이 1이기 때문에, 1을 대입하고 시작

		while (!q.isEmpty()) {

			Point p = q.poll();

			for (int c = 0; c < 4; c++) {
				int nx = p.x + dx[c];
				int ny = p.y + dy[c];

				// 1. 범위 먼저
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				// 2. 해당 지도의 영역이 1인지 확인
				if (map[nx][ny] == 0) {
					continue;
				}

				// 3. 방문 여부 확인
				if (visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				q.add(new Point(nx, ny));

				count++;

			}
		}
		return count;
	}
}

