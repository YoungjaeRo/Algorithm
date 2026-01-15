import java.io.*;
import java.util.*;
import java.math.*;


public class Main {
	/**
	 * n C m을 출력하시오
	 * n * n - 1 * n - 2 * n - 3 ....m   /  m * m - 1 * ..1
	 *
	 * 조합 문제여서, 백트래킹을 생각할 수 있지만,
	 * 따로 조건이 없고, 조합들을 출력할 필요가 없기 때문에,
	 * 그냥 파스칼 삼각형 공식을 통해서 답을 구한다
	 *
	 * nCm = (n - 1) C (m - 1) + (n-1) C m
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());


		/**
		 * dp[i][j] = iCj 를 BigInteger로 저장
		 * 왜 BigInteger?
		 * - 100C50 같은 값이 long을 훨씬 넘어가서(자릿수 29자리 이상) long으로는 절대 못 담음
		 */

		BigInteger[][] dp = new BigInteger[n + 1][m + 1];


		/**
		 * i = 0부터 n까지 "행"을 만들어가면서
		 * j = 0부터 min(i, m)까지 "열"을 채운다.
		 *
		 * 왜 min(i, m)?
		 * - iCj에서 j가 i보다 크면 의미가 없음 (예: 3C5 같은 건 없음)
		 * - 그리고 우리는 m까지만 필요하니까 더 이상 계산할 필요도 없음
		 */

		// 파스칼 삼각형
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= Math.min(i, m); j++) {

				if(j == 0 || j == i) {
					dp[i][j] = BigInteger.ONE;
				} else {
					dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
				}
			}
		}

		System.out.println(dp[n][m]);
	}
}
