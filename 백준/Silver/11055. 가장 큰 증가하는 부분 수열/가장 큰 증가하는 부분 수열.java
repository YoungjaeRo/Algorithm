import java.io.*;
import java.util.*;

public class Main {

	static int N; // 수열의 크기

	static int[] dp;
	static int[] arr;

	/**
	 * dp[N] = N번째 원소가 마지막인 가장 큰 값
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dp = new int[N];
		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {

		arr[i] = Integer.parseInt(st.nextToken());

		}

		// 기저값 세팅 : 처음엔 본인 인덱스의 값으로
		for(int i = 0; i < N; i++) {
			dp[i] = arr[i];
		}

		// 점화식 실행
		for(int i = 0; i < N; i++) {

			for(int j = 0; j < i; j++) {

				// 증가하는 수열 조건
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
		}

		int max = Integer.MIN_VALUE;

		for(int m : dp) {
			if(m > max) {
				max = m;
			}
		}

		System.out.println(max);

	}
}
