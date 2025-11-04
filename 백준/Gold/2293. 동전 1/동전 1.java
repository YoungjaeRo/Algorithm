import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 동전 개수
		int K = Integer.parseInt(st.nextToken()); //  목표 금액


		int[] coin = new int[N]; // 동전 종류 배열

		// 동전 정보 주입하기 ㅇㅇ
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		// dp[x] = x원을 만드는 경우의 수
		// dp[5] = 5원을 만드는 조합의 개수

		long[] dp = new long[K + 1];
		dp[0] = 1; // 0원을 만드는 방법은 1가지

		for(int i = 0; i < N; i++) { // 각 동전마다
			int c = coin[i]; // 현재 사용하는 동전

			/**
			 * j: 현재 계산할 금액
			 * j - c : c원을 한 번 사용하기 전 금액
			 *
			 * dp[j] += dp[j - c]
			 * => (j-c)원을 만든 모든 방법에 c원을 하나 더 붙이면 j원을 만들 수 있다.
			 * => 이렇게 누적하면서 모든 조합을 센다.
			 */

			for(int j = c; j <= K; j++) { // c원 부터 k 원까지
				dp[j] = dp[j] + dp[j - c];

			}
		}

		System.out.println(dp[K]);
	}

}
