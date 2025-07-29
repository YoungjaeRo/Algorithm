import java.util.*;
import java.io.*;

// map[y][x] => map[세로][가로] = map[행][열]

public class Main {
	static int T; // 테스트케이스 수
	static int M; // 가로길이
	static int N; // 세로길이
	static int K; // 배추의 개수


	static int[][] map; //  배추밭

	static boolean[][] visited; // BFS DFS는 방문체크가 필수

	// 상하좌우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};


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

		T = Integer.parseInt(br.readLine());


		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 가로
			N = Integer.parseInt(st.nextToken());  // 세로
			K = Integer.parseInt(st.nextToken());  // 배추 수

			map = new int[M][N];
			visited = new boolean[M][N];

			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}

			int count = 0;
			for(int x = 0; x < M; x++) {
				for(int y = 0; y < N; y++) {
					if(map[x][y] == 1 && !visited[x][y]) {
						bfs(x, y);
						count++;
					}
				}
			}
            
            System.out.println(count);
		}
	}

	static void bfs(int startX,int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(startX, startY));
		visited[startX][startY] = true;


		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				//  범위체크
				if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
					if(map[nx][ny] == 1 && !visited[nx][ny]) {
						queue.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				};
			}
		}
	}
}
