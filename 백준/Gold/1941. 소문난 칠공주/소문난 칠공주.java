import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1. 7명의 학생들로 이루어져있어야 한다
	 *
	 * 2. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야한다
	 *
	 * 3. 화합과 번영을 위해, 반드시 이다솜파 학생들로만 이루어져 있을필요는 없다
	 *
	 * 4. 그래로 7명중 적어도 4명 이상은 반드시 포함되어 있어야한다.
	 *
	 * 모든 경우의 수 -- > 백트래킹
	 *
	 * 1.  일단은 백트래킹으로 조합을 구하고,
	 * 2. 그 이후 조건에 부합하는지 검증해본다
	 */

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static char[][] map = new char[5][5];
	static boolean[] selected = new boolean[25];

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 그래프 정보 입력
		for(int i = 0; i < 5; i++) {
			String line = br.readLine();

			for(int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		// 백트래킹 시작
		dfs(0, 0, 0);
		
		System.out.println(answer);
	}

	static void dfs(int start, int depth, int sCount) {

		// 1. 가지치기 (되려 임도연 파가, 이다솜파보다 숫자가 더 많다면)
		if(depth - sCount >= 4) return;

		if(depth == 7) {
			if(sCount >= 4 && isConnected()) {
				// 조합 1개 완성
				answer++;
			}
			return;
		}

		// 조합 시작
		for(int i = start; i < 25; i++) {
			selected[i] = true;

			int r = i / 5;
			int c = i % 5;

			// 이번 선택이 S면, S 증가시켜주기
			int newSCount = sCount;
			if(map[r][c] == 'S') {
				newSCount++;
			}

			// 다음 후보 진행 (조합이니까 i + 1 부터)
			dfs(i + 1, depth + 1, newSCount);

			selected[i] = false;
		}
	}

	static boolean isConnected() {
		ArrayDeque<Integer> q = new ArrayDeque<>();

		// 꼭 방문 배열이 필요하다
		boolean[] visited = new boolean[25];

		int start = -1;
		
		int cnt = 1;
		

		// 선택된 숫자일때, bfs 탐색을 시작한다.
		for(int i = 0; i < 25; i++) {
			if(selected[i]) {
				start = i;
				break;
			}
		}

		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			// 다시 좌표로 바꿔줘야 한다
			int r = cur / 5;
			int c = cur % 5;

			for(int d = 0; d < 4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];


				// 1. 범위체크
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
					continue;
				}

				int nxt = nr * 5 + nc;

				if(!selected[nxt]) {
					continue;
				}

				if(!visited[nxt]) {
					visited[nxt] = true;
					q.offer(nxt);
					cnt++;
					
				}
			}
		}
		
		if(cnt == 7) {
			return true;
		} else {
			return false;
		}
	}
}
