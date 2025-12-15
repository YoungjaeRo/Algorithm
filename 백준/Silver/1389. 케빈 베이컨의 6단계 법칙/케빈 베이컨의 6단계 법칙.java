import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 모든 사람들은, 최대 6단계 이내에서 서로 아는 사람으로 연결된다
	 * 벌써 부터 플로이드 워셜 알고리즘의 냄새가 남
	 *
	 * 케빈 베이컨 게임 : 두사람이 최소 몇단계 만에 이어질 수 있는지 계산하는 게임
	 *
	 * 문제가 원하는 것 : 케빈 베이컨의 수가 가장 작은 사람을 구하시오 ㅇㅇ
	 */
	static int N; // 유저의 수
	static int M; // 친구 관계의 수

	static final int INF = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 플로이드는 인접행렬 풀기 때문에 2차워 배열을 선언한다
		int[][] floyd = new int[N + 1][N + 1]; // 사람의 번호가 1부터 N까지 이기 때문에


		// 1. 일단은 모든 값들을 충분히 큰 값으로 초기화 해준다, 단 나 자신과의 거리는 0
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					floyd[i][j] = 0;
				} else {
					floyd[i][j] = INF;
				}
			}
		}

		// 2. 간선 입력(무방향, 친구면 거리는 1)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			// 서로 친구 관계 정립해주기  (1을 대입)
			floyd[s][e] = 1;
			floyd[e][s] = 1;

		}

		// 플로이드 워셜 알고리즘 시작
		for(int k = 1; k <= N; k++) {

			for(int i = 1; i <= N; i++) {

				for(int j = 1; j <= N; j++) {
					
					if(floyd[i][k] != INF && floyd[k][j] != INF) {
						floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
					}
				}
			}
		}

		// 베이컨의 수가 가장 작은 사람을 출력 : 행 단위 총합을 기준으로 자르면 됨
		int answer = 1; // 정답이 될 사람의 번호
		int minSum = INF; // 최소 베이컨 수

		for(int i = 1; i <= N; i++) { // 각 사람 i 기준
			int sum = 0;

			for(int j = 1; j <= N; j++) {
				sum = sum + floyd[i][j];
			}
			if(sum < minSum) {
				minSum = sum;
				answer = i;
			}
		}

		System.out.println(answer);

	}

}
