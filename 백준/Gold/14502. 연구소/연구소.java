import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	static int[][] map;
	static int[][] copyMap;

	// 상, 하, 좌, 우

	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};

	static int maxSafe = 0;

	static class Virus {
		int x;
		int y;

		Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copyMap = new int[N][M];

		// 1. 지도 입력 받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		// 2. DFS로 벽 3개를 세울 수 있는 조합 선택
		dfs(0);

		System.out.println(maxSafe);

	}

	static void dfs(int cnt) {
		// 꼭 종료조건이 필요하다
		if(cnt == 3) {
			bfs();
			return;
		}

		// 모든 칸을 돌면서 0인 칸에 벽을 세워보고, 다시 원복을 조짐
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1; // 벽 세우기
					dfs(cnt + 1); // 다음 벽 세우기 위해 재귀 함수 실행
					map[i][j] = 0; // 원복
				}
			}
		}
	}

	static void bfs() {
		Queue<Virus> q = new LinkedList<>();

		// copyMap으로 깊은 복사
		for(int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		// 2. 현재 지도에서 바이러스 인 칸을 전부 큐에 넣기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 2) {
					q.offer(new Virus(i, j));
				}
			}
		}

		// 3. 바이러스 퍼트리기 시작
		while(!q.isEmpty()) {
			Virus v = q.poll();

			for(int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if(copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = 2; // 감염시키기
					q.offer(new Virus(nx, ny));
				}

			}
		}
		
		// 4. 안전영역 계산하기
		int safe = getSafeArea();
		
		// 5. 최대값 갱신
		maxSafe = Math.max(maxSafe, safe);
	}

	static int getSafeArea() {
		int safe = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) {
					safe++;
				}
			}
		}
		return safe;
	}
}
