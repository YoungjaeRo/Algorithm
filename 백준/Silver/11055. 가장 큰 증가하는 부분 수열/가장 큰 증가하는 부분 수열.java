import java.io.*;
import java.util.*;

public class Main {

	static int N; // 수열의 크기

	/**
	 * 증가하는 부분 수열 중 합이 가장 큰 것
	 * dp[i] = i 번째 원소를 마지막으로 했을때, 얻을 수 있는 최댓값
	 */

	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;

		// dp 시작
		for(int i = 1; i <= N; i++) {

			dp[i] = arr[i]; // 각자 본인으로 초기화

			for(int j = 1; j < i; j++) {

				// 증가 수열 조건과 맞는다면,
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}

			ans = Math.max(ans, dp[i]);
		}

		System.out.println(ans);
	}
}
