import java.util.*;
import java.io.*;


public class Main {

	static int M, N;
	static int K;

	static int [][] map;
	static boolean [][] visited;

	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};



	static class Point{
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

				// 주어진 두 점들 사이 영역에 직사각형 색칠하기
			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					map[y][x] = 1;
				}
			}
		}

		int count = 0;
		ArrayList<Integer> areas = new ArrayList<>();

		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					int data = bfs(i,j);
					areas.add(data);
					count++;
				}
			}
		}

		Collections.sort(areas);
		System.out.println(count);

		for(int a : areas) {
			System.out.print(a + " ");
		}
	}

	static int bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(i, j));
		visited[i][j] = true;

		int area = 1;

		while(!q.isEmpty()) {
			Point p = q.poll();
			int cx = p.x;
			int cy = p.y;

			for(int c = 0; c < 4; c++) {
				int nx = cx + dx[c];
				int ny = cy + dy[c];

				if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
					if(map[nx][ny] == 0 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny));
						area++;
					}
				}

			}
		}
		return area;
	}
}
