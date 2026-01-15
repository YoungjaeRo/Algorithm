import java.io.*;
import java.util.*;

public class Main {
	/**
	 * n개의 동전,
	 * 해당 동전들을 적당히 사용해서, 가치의 합이 k원이 되도록 하고 싶다
	 * 그러면서 최소의 개수가 되도록 하고 싶음
	 *
	 *
	 * 일단, 최소/최대/ 경우의 수를 물어봄
	 * ~ 했을때의 최적값
	 * 앞의 선택이 뒤에도 영향을 준다
	 * 같은 계산을 계속 반복하는 구조
	 *
	 *
	 * dp[x] = x금액을 만들기 위한 최소 동전 개수
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] coins = new int[N];

		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		 // dp[x] = x 원을 만드는데, 필요한 최소 동전의 개수

		final int INF = 1000000;

		int[] dp = new int[K + 1];

		// 만들 수 없으면, INF로 표시한다.
		Arrays.fill(dp, INF);


		// 기저값 세팅
		dp[0] = 0; // 0원을 만들기 위해선, 동전이 필요없음

		// 동전을 하나씩 다 훑어보면서 dp 값 세팅
		for(int coin : coins) {

			// coin을 여러번 사용할 수 있기때문에, 앞에서 뒤로 ㅇㅇ

			for(int money = coin; money <= K; money++) {
				// money - coin을 만들 수 있었다면,
				// 거기에 coin 1개 추가해서 money를 만들 수 있음
				if(dp[money - coin] != INF) {
					dp[money] = Math.min(dp[money], dp[money - coin] + 1);
				}
			}
		}

		System.out.println(dp[K] == INF ? -1 : dp[K]);

	}
}
