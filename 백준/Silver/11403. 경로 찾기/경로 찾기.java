import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 모든 정점을 언급했으므로, 다익스트라가 아닌, 플로이드-워셜이다.
	 * 노드의 개수도 100개 이하로 매우 적기 때문에, 플로이드 워셜로 풀이가 가능하다
	 */

	static int N;
	static int[][] graph; // 플로이드워셜은 인접리스트가 아닌, '인접행렬'로 세팅하게 됨

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 정점의 개수
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];

		// 인접행렬 값 입력하기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드 워셜 함수 시작

		// 거쳐가는 노드
		for(int k = 0; k < N; k++) {

			// 출발노드
			for(int s = 0; s < N; s++) {

				// 도착 노드

				for(int e = 0;  e < N; e++) {

					// s -- > k로 갈 수 있고, k -- > e로 갈 수 있다면,
					if(graph[s][k] == 1 && graph[k][e] == 1) {
						// 결과적으로 s -- > e까지 도달이 가능하다
						graph[s][e] = 1;

					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
