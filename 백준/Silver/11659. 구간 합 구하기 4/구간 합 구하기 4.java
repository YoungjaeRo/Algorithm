import java.io.*;
import java.util.*;

public class Main {
	static int N; // 수의 개수
	static int M; // 합을 구해야 하는 횟수

	static int[] dp; // i 지점까지의 합

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		int sum = 0;

		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum = sum + arr[i];
			dp[i] = sum;
		}

		for(int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());

			int to = Integer.parseInt(st.nextToken());

			int answer = dp[to] - dp[from - 1];
			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}

}
