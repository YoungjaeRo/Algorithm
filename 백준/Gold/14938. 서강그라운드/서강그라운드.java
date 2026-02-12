import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 각각의 거리까지의 최단거리를 구해서 풀어야 하므로 플로이드-워셜 알고리즘이 필요하다
	 */

	static final int INF = Integer.MAX_VALUE;

	static int n; // 지역 개수
	static int m; // 수색 범위
	static int r; // 길의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		// 플로이드 워셜은 인접행렬
		int[][] dist = new int[n + 1][n + 1];

		for(int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		// 각 구역에 따른 아이템 수 저장하기
		int[] items = new int[n + 1];

		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		// 문제 조건처럼 양방향 처리를 해줘야 한다
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			dist[start][end] = Math.min(cost, dist[start][end]);

			dist[end][start] = Math.min(cost, dist[end][start]);
		}

		// 플로이드 워셜 시작

		for(int k = 1; k <= n; k++) {

			for(int i = 1; i <= n; i++) {

				for(int j = 1; j <= n; j++) {

					if(dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}


					int nextDist = dist[i][k] + dist[k][j];

					if(nextDist < dist[i][j]) {
						dist[i][j] = nextDist;
					}
				}
			}
		}


		// 각 시작점마다 m이내로 오면 아이템 회득 가능

		int total = 0; // 총 가질 수 있는 아이템 수


		for(int i = 1; i <= n; i++) {
			int start = 0; // 각 i 지점에서 시작했을때, 얻을 수 있는 아이템 수

			for(int j = 1; j <= n; j++) {
				if(dist[i][j] <= m) {
					start = start + items[j];
				}
			}

			total = Math.max(total, start);
		}

		System.out.println(total);
	}
}
