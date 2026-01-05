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

		int[] W = new int[N + 1]; // 무게
		int[] V = new int[N + 1]; // 가치

		// 1번부터 N번까지의 물건의 정보 입력 받기
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		// (바텀업)
		// dp[i][w] = i번째 물건까지 고려했을 때, 무게 w로 얻을 수 있는 최대 가치
		int[][] dp = new int[N + 1][K + 1];

		for(int i = 1; i <= N; i++) {

			for(int w = 0; w <= K; w++) {

				// 1.  물건을 넣지 않을때
				dp[i][w] = dp[i - 1][w];

				// 2. 물건을 넣을 수 있을때
				if(w >= W[i]) {
					int takeValue = dp[i - 1][w - W[i]] + V[i];
					dp[i][w] = Math.max(dp[i][w], takeValue);
				}
			}
		}
		System.out.println(dp[N][K]);

	}

}
