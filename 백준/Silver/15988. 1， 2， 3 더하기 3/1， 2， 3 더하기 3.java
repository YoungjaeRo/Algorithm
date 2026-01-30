import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 숫자 1,2,3의 합으로 숫자 n을 만드는 방법의 개수를 구하시오
	 * dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
	 */

	static int T;

	static final long MOD = 1000000009L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케 갯수
		T = Integer.parseInt(br.readLine());

		// 각 테케의 n값을 저장할 배열
		int[] query = new int[T];

		// n 값중 가장 큰값
		int maxN = 0;

		for(int i = 0; i < T; i++) {
			query[i] = Integer.parseInt(br.readLine());

			if(query[i] > maxN) {
				maxN = query[i];
			}
		}

		long[] dp = new long[maxN + 1];

		// 기저값 설정
		dp[0] = 1;
		if(maxN >= 1) dp[1] = 1;
		if(maxN >= 2) dp[2] = 2;
		if(maxN >= 3) dp[3] = 4;


		for(int i = 4; i <= maxN; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			sb.append(dp[query[i]]).append('\n');
		}
        
        System.out.println(sb);

	}
}
