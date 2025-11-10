import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		// T[i] : i번째 날에 상담을 시작하면 걸리는 기간 (며칠짜리인지)
		int[] T = new int[N + 2];


		// P[i] : i번째 날에 상담을 하면 받을 수 있는 금액(보상)
		int[] P = new int[N + 2];

		// 배열 크기를 N+2로 한 이유:
		//   - N일까지 일할 수 있지만, i + T[i]가 N+1이 될 수도 있음
		//   - 즉, "퇴사 다음 날"까지 dp 계산해야 하므로 여유 공간 한 칸 더 줌


		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken()); // 상담 기간 입력
			P[i] = Integer.parseInt(st.nextToken()); // 상담 보상 입력
		}

		// dp[i] : i일까지 얻을 수 있는 최대 수익
		// long으로 선언 → 합계가 int 범위를 넘어갈 수도 있음

		long[] dp = new long[N + 2];

		for(int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			int end = i + T[i];

			if(end <= N + 1) {// (3) 퇴사 전이면 유효
				dp[end] = Math.max(dp[end], dp[i] + P[i]); // 끝나는 날 이익을 반영
			}
		}

		dp[N + 1] = Math.max(dp[N + 1], dp[N]);
		
		// 최종 최대 이익 출력
		System.out.println(dp[N + 1]);
	}
}
