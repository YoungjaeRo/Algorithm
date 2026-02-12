import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int n; // 도시의 개수

	static int m; // 버스의 개수

	// 모든 쌍에 대한 최소비용 -- > 플로이드워셜

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		// 플로이드 워셜은 인접행렬이 필요하다(2차원 배열)
		int[][] dist = new int[n + 1][n + 1];

		for(int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		// 경로 추적용 배열
		int[][] route = new int[n + 1][n + 1];

		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if(cost < dist[start][end]) {
				dist[start][end] = cost;
				route[start][end] = end;
			}
		}


		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {

					// 경로가 없으면 계산을 하지 않는다.
					if(dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}

					int nextDist = dist[i][k] + dist[k][j];

					if(nextDist < dist[i][j]) {
						//최단 거리 갱신  & 경로 추적 업데이트
						dist[i][j] = nextDist;
						route[i][j] = route[i][k];
					}
				}
			}
		}


		// 거리 출력하기
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
			}
			sb.append("\n");
		}

		// 경로 출력
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				
				if(i == j || route[i][j] == 0) {
					sb.append(0).append(" ");
					continue;
				}
				
				List<Integer> path = new ArrayList<>();
				int cur = i;
				path.add(cur);
				
				while(cur != j) {
					cur = route[cur][j];
					path.add(cur);
				}
				
				sb.append(path.size()).append(" ");
				
				for(int v : path) {
					sb.append(v).append(" ");
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}
}
