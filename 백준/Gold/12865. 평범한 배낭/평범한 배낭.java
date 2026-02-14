import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N개의 물건이 있음
	 *
	 * 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면,
	 * 준서는 V만큼 즐길 수 있다
	 *
	 * 최대 K 만큼의 무게를 넣을 수 있는 배낭만 들고 다닐수 있다
	 *
	 * 준서가 최대한 즐거운 여행을 하기 위해서 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 구하여라
	 */

	static int N;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 물건의 개수
		K = Integer.parseInt(st.nextToken()); // 최대 무게

		/**
		 * i번째 물건까지 고려했을때, 무게 j에서의 최대가치
		 */
		int[][] dp = new int[N + 1][K + 1];

		int[] weight = new int[N + 1];
		int[] value = new int[N + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			weight[i] = Integer.parseInt(st.nextToken());

			value[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1; i <= N; i++) { // 물건 하나하나를 훑으면서

			for(int j = 0; j <= K; j++) { // 무게 제한

				// 지금 물건을 넣지 않을때,
				dp[i][j] = dp[i -1][j];

				// 해당 물건을 넣을 수 있을때,
				if(weight[i] <= j) {
					int takeValue = dp[i - 1][j - weight[i]] + value[i];
					dp[i][j] = Math.max(dp[i][j], takeValue);
				}

			}
		}

		System.out.println(dp[N][K]);

	}

}
