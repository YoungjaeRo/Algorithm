import java.util.*;
import java.io.*;


public class Main {
	/**
	 *  m * n 크기의 모눈종이,
	 *  bfs 로 돌면서 면적의 넓이를 구하고, 그 다음 흠,,,,
	 *  왼쪽 꼭지점, 오른쪽 꼭지점을 주어준다면,
	 *  넓이 = (N - 0) * (M - 0)
	 */

	static int M;

	static int N;

	static int K; // 직사각형 개수

	static int[][] map;

	static boolean[][] visited;

	static int[] dx = {-1, 1, 0, 0};

	static int[] dy = {0, 0, -1, 1};

	static int count; // 나눠진 영역의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

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

			// 주어진 대로 칠하기
			for(int y = y1; y < y2; y++) {
				for(int x = x1; x < x2; x++) {
					map[y][x] = 1;
					visited[y][x] = true;
				}
			}
		}

		// 넓이 정렬하기 위해서 (오름차순) 리스트 선언
		List<Integer> list = new ArrayList<>();


		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					count++;
					int size = bfs(i, j);
					list.add(size);
				}
			}
		}

		Collections.sort(list);

		sb.append(count).append("\n");

		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ");
		}

		System.out.println(sb);
	}

	static int bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;

		q.offer(new int[] {x, y});

		int size = 1;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];

			for(int i = 0; i < 4; i++) {
				int nX = curX + dx[i];
				int nY = curY + dy[i];

				// 범위밖이거나
				if(nX < 0 || nX >= M || nY < 0 || nY >= N) {
					continue;
				}

				// 칠해졌거나, 방문한적이 있거나
				if(map[nX][nY] == 1 || visited[nX][nY]) {
					continue;
				}

				visited[nX][nY] = true;
				size++;
				q.offer(new int[] {nX, nY});
			}
		}

		return size;
	}
}
