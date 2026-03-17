import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 최소 또는 최대가 되는 경로를 추출하라고 했기 떄문에,
	 * 작으 문제를 통해서 큰 문제의 해답을 찾을 수 있다 그래서 dp를 이용해 문제를 푼다
	 *
	 * dp[i][j] = i행 j열까지의 최대 경로 값
	 */

	static int n; // 삼각형의 크기
	static int[][] arr;

	static int[][] dp;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		dp = new int[n][n];

		// 삼격형 값 정보 주입 받기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp 기저값 세팅하기
		dp[0][0] = arr[0][0];

		for(int i = 1; i < n; i++) {

			for(int j = 0; j <= i; j++) {

				// 각 위치에 따른 최대값을 넣어주는 메커니즘이 다르다

				// 가장 왼쪽
				if(j == 0) {
					dp[i][j] = dp[i - 1][0] + arr[i][j];

					// 가장 오른쪽
				} else if(j == i) {
					dp[i][j] = dp[i - 1][j - 1] + arr[i][j];
				}
				// 그외는 양갈래 중 가장 큰 값을 입력 받아야 한다/
				else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
				}
			}
		}

		int max = Integer.MIN_VALUE;

		for(int i = 0; i < n; i++) {
			if(dp[n - 1][i] > max) {
				max = dp[n - 1][i];
			}
		}

		System.out.println(max);

	}
}
