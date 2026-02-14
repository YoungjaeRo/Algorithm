import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 1; i<= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		/**
		 * dp[i] : i번째 원소를 마지막으로 하는, 가장 긴 부분 수열의 길이
		 */

		Arrays.fill(dp, 1); // 길이 1로 다 초기화

		for(int i = 1; i <= N; i++) {

			for(int j = 1; j < i; j++) {

				// 증가하는 수열 조건이 된다면
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int ans = 0;
		for(int a : dp) {
			if(a > ans) {
				ans = a;
			}
		}

		System.out.println(ans);
	}
}
