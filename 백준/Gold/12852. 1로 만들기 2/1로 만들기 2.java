import java.io.*;
import java.util.*;

public class Main {
	/**
	 * X가 3으로 나누어 떨어지면 3으로 나눈다
	 * X가 2로 나누어 떨어지면 2로 나눈다
	 *
	 * 1을 뺀다
	 *
	 *
	 * 3가지를 적절히 사용해서 1을 만들려고 한다
	 *
	 * 정수 N을 1로 만들기 위해 필요한 최소한의 연산 횟수
	 * dp[N] = dp[N / 3] + dp[N / 2] + dp[N - 1]
	 */

	static int N;

	static int[] dp;

	static int[] next;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// dp[N] : N을 1로 만들기 위해 필요한 최소한의 연산 횟수
		dp = new int[N + 1];

		// next[i] = dp[i]가 최소가 되기 위해, 이동하는 경로
		// next[10] = 9 이면, 10은 9에서 왔다(즉 경로는 10 -> 9 -> ...)
		next = new int[N + 1];

		// 기저값 설정
		dp[1] = 0; // 이미 1이기 때문에, 연산이 필요없음 ㅇㅇ 연산횟수 0회
		next[1] = 0;

		for(int i = 2; i <= N; i++) {


			// 1. 기본 선택 -- > -1 연산을 할때
			dp[i] = dp[i - 1] + 1;

			next[i] = i - 1;


			// 2. 2로 나누어 떨어질때, 2로 나누는 연산
			if(i % 2 == 0) {
				int cand = dp[i / 2] + 1;
				if(cand < dp[i]) {
					dp[i] = cand;
					next[i] = i / 2;
				}
			}
			
			// 3. 3으로 나누어 떨어질떄, 3으로 나누는 연산
			if(i %  3 == 0) {
				int cand = dp[i / 3] + 1;
				if(cand < dp[i]) {
					dp[i] = cand;
					next[i] = i / 3;
				}
			}
			
		}
		
		// 최소 연산 횟수 출력하기
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N]).append("\n");
		
		// 경로 출력
		// N에서 시작해서 next까지 차근 차근 따라가면, 1까지 가는 실제 수열의 경로를 구할수 있다
		int cur = N;
		
		while(true) {
		sb.append(cur).append(' ');
		
		if(cur == 1) {
			break;
		}
		cur = next[cur];
		}

		System.out.println(sb.toString());

	}
}
