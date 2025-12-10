import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 서로 모르는 사람도 존재하지만, 몇사람을 통하면 모두가 서로를 알수 있다.
	 * 플로이드워셜??
	 *
	 * 각 회원은 다른 회원과 가까운 정도에 따라 점수를 받게됨
	 * 1점 : 어느 회원이 다른 모든 회원과 친구인 경우
	 * 2점 : 다른 모든 회원이 친구이거나, 친구의 친구임
	 * 3점 : 다르 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구이거나
	 *
	 * 그래프인데, 깊이가 1,2,3에 따라서 점수를 주는듯
	 *
	 *  ** 두 회원이 친구이면서, 동시에 친구의 친구사이이면, 그둘은 친구사이
	 *  회장인 점수가 가장 적은 사람
	 *  회장의 점수와 회장이 될수 있는 모든 사람을 출력
	 */

	static int N; // 회원 수

	static final int INF = 1000000;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	N = Integer.parseInt(br.readLine());

	 // 1. 플로이드 워셜은 인접 행렬로 풀어야하므로 2차원 배열을 선언해준다.
		int[][] dist = new int[N + 1][N + 1];

		// 2. 배열을 초기화 해준다. (본인과 본인과의 거리는 0 나머지는 충분히 큰값)
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					dist[i][j] = 0;
				} else {
					dist[i][j] = INF;
				}
			}
		}

		// 친구 관계를 입력
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(a == -1  && b == -1) {
				// 반복문 빠져나옴
				break;
			}

			dist[a][b] = 1;
			dist[b][a] = 1; // 이런식으로 양방향으로 값 1을 넣어준다. 친구니까 값이 1
		}

		/**
		 * 플로이드 워셜을 통해서, 모든 노드간의 최단거리를 구하기
		 *  // 모든 정점 쌍 i→j 의 최단거리를 계산하는 알고리즘
		 *  // dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
		 */

		// 거쳐가는 노드
		for(int k = 1; k <= N; k++) {

			// 시작 노드
			for(int i = 1; i <= N; i++) {

				// 도착 노드
				for(int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

				}
			}
		}

		// 4. 각 회원의 점수 정산
		// 여기서 점수는 최단거리중 가장 최대값이 해당 회원의 점수가 된다.

		int minScore = INF; // 전체 회원중 최소 점수 (회장의 점수)
		List<Integer> candidates = new ArrayList<>();

		for(int i = 1; i <= N; i++) {
			int score = 0; // i 번 회원의 점수  == dist[i][1...까지의] 최댓값

			for(int j = 1; j <= N; j++) {
				score = Math.max(score, dist[i][j]);
			}

			// 지금까지 찾은 최소 점수보다 더 낮으면, 회장 후보로 임명
			if(score < minScore) {
				minScore = score; // 최소 점수를 갱신
				candidates.clear(); // 새로운 최저 점수의 후보가 들어왔기 떄문에, 리스트 초기화
				candidates.add(i); // 새로운 후보 등록

			} else if(score == minScore) { // 점수가 같으면, 후보로만 등록
				candidates.add(i);

			}
		}

		// 5. 출력 : 첫줄엔, 최소 점수 + 후보의 수   둘째 줄엔, 후보의 번호들
		StringBuilder sb = new StringBuilder();
		sb.append(minScore).append(" ").append(candidates.size()).append("\n");

		for(int num : candidates) {
			sb.append(num).append(" ");
		}

		System.out.println(sb.toString());

	}
}
