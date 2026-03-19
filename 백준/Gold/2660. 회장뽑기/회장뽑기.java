import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 각 사람과 사람끼리의 거리를 구해야 하므로, 다익스트라 보단, 플로이드 워셜 알고리즘을 사용해야 한다.
	 */

	static final int INF = 10000000;

	static int N;
	static StringTokenizer st;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];

		// 플로이드 워셜 배열 초기화 해주기 : 자기 자신과의 거리는 0, 그 외는 충분히 큰값으로
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					map[i][j] = 0;
				} else {
					map[i][j] = INF;
				}
			}
		}

		// 이제 문제에서 주어지는 친구 관계 확립하기, 직접적인 친구관계는 1로 초기화 한다.
		while(true) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(a == -1 && b == -1) {
				break;
			}

			map[a][b] = 1;
			map[b][a] = 1;
		}


		// 플로이드 워셜 알고리즘 시작
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {

					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}

		// 이제 각각의 최소 길이(관계레벨)를 업데이트 해주었기 때문에,
		// 각 i번째 사람들의 각 사람들에 대한 레벨을 탐색하고, 그 중 가장 큰 값이 그 사람의 관계 레벨이다

		List<Integer> candid = new ArrayList<>();

		// 전체 회원중 최소 점수(회장의 점수)
		int min = Integer.MAX_VALUE;

		for(int i = 1; i <= N; i++) {

			int score = 0;

			for(int j = 1; j <= N; j++) {
				if(map[i][j] > score) {
					score = map[i][j];
				}
			}

			// 새롭게 더 작은 관계 레벨의 사람을 찾게 된다면, 업데이트 해주기
			if(score < min) {
				min = score;

				candid.clear();
				candid.add(i);
			} else if(score == min) {
				candid.add(i);
			}
		}

		// 정답 출력하기
		sb.append(min).append(" ").append(candid.size()).append("\n");

		for(int c : candid) {
			sb.append(c).append(" ");
		}

		System.out.println(sb);

	}
}
