import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 집의 수 N
		int N = Integer.parseInt(br.readLine());

		// cost[i][j] = i 번째 집을 j색으로 칠할 때의 비용
		int[][] cost = new int[N][3];

		// 입력 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			cost[i][1] = Integer.parseInt(st.nextToken()); // 초록
			cost[i][2] = Integer.parseInt(st.nextToken()); // 파랑
		}

		// DP 배열 : dp[i][j] = i 번째 집까지 칠할때, j 색으로 끝나는 최소 비용
		int[][] dp = new int[N][3];

		// 초기값: 첫 번째 집은 그냥 칠하는 비용 그대로
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];

		// 두번째 집부터 DP 채우기
		for(int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0]; // 전에는 초록 또는 파란 현재는 "빨강"
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1]; // 전에는 빨강 또는 파란 현재는 "초록"
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2]; // 전에는 초록 또는 빨강 현재는 "파란"
		}

		// 마지막 집까지 칠한 뒤, 가장 싼 경우 출력
		int answer = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);
		System.out.println(answer);
	}
}
