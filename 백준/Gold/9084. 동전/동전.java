import java.io.*;
import java.util.*;

public class Main {

	static int T; // 테스트케이스 수
	static int N; // 동전의 개수
	static int M; // 만들어야 할 금액


	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());

			int[] coins = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			// 목표금액 입력받기
			M = Integer.parseInt(br.readLine());

			int[] dp = new int[M + 1];

			dp[0] = 1; // 0원을 만들기 위해 아무것도 사용하지 않는 경우의 수 1가지만 존재


			// dp[money] = money 금액을 만들 수 있는 경우의 수
			for(int i = 0; i < N; i++) {
				int coin = coins[i];


				for(int money = coin; money <= M; money++) {
					dp[money] = dp[money] + dp[money - coin];
				}
			}

			sb.append(dp[M]).append("\n");
		}

		System.out.println(sb);
	}
}
