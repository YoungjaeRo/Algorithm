import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int K;

	static int[][] dp; // i 번째 물건까지 고려했을때, 무게 w로 얻을 수 있는 최대 가치

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// dp[i][w] = i번째 배당에서 무게 제한 w에서 얻을 수 있는 최대 가치
		dp = new int[N + 1][K + 1];

		int[] w = new int[N + 1];
		int[] v = new int[K + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}


		// dp 시작
		for(int i = 1; i <= N; i++) {

			for(int j = 0; j <= K; j++) {

				// 물건을 담을 수 없을 때
				dp[i][j] = dp[i - 1][j];

				// 물건을 담을 수 있을 때(무게가 가능할때)
				if(w[i] <= j) {
					int intake = dp[i - 1][j - w[i]] + v[i];

					dp[i][j] = Math.max(dp[i][j], intake);
				}
			}
		}

		System.out.println(dp[N][K]);

	}

}
