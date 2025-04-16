import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 해당 문제를 플로이드 워셜로 풀어야겠다고 생각하게 된 이유
 * 1. 일단 노드의 개수 N이 매우 작다 --> 100~ 200개 이하
 * 2. 특정 S, E까지의 최단거리가 아니라, 모든 도시 쌍에 관해서 최단거리를 구하라고 함
 */
public class Main {
	static int[][] dist;
	static int n, m;
	static final int INF = 10000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		dist = new int[n + 1][n + 1];

		// 거리 배열 초기화
		for(int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0; // 자기자신은 0을 초기화
		}

		// 간선 정보 입력
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 여러개의 버스가 있을 수 있으므로 최소 비용만 저장
			dist[a][b] = Math.min(dist[a][b], c);
		}

		// 플로이드 워셀 알고리즘
		for(int k = 1; k <= n; k++) {
			for(int s = 1; s <= n; s++) {
				for(int e = 1; e <=n; e++) {
					if(dist[s][e] > dist[s][k] + dist[k][e]) {
						dist[s][e] = dist[s][k] + dist[k][e];
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(dist[i][j] == INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(dist[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
