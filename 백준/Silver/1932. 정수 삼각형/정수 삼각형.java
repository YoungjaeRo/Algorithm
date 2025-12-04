import java.io.*;
import java.util.*;

public class Main {
	/**
	 [이 문제에서 DP 설계]
	 *  - dp[i][j]의 의미를 다음처럼 정의:
	 *      "i번째 줄의 j번째 칸까지 내려올 때 만들 수 있는 '최대 합'
	 *  - 원래 삼각형: triangle[i][j]
	 *  - 최대 합을 저장하는 배열: dp[i][j]
	 */

	static int N; // 삼각형의 크기 (줄 개수)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// 편의를 위해서 삼격형보단 N * N  배열을 만든후, 값을 저장
		int[][] triangle = new int[N][N];

		//dp[i][j] : i 번째 줄 j번째 원소까지의 최대합

		int[][] dp = new int[N][N];


		// 1. 삼각형 입력 받기
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			//  j는 0~ i까지만 값이 존재한다
			for(int j = 0; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		// 2. DP 기저값 설정

		dp[0][0] = triangle[0][0];


		// dp 점화식 값 채우기 시작
		for(int i = 1; i < N; i++) { // 0번째, 맨 꼭대기는 채웠기 때문에, 1번째 줄부터 채우기 시작

			for(int j = 0; j <= i; j++) {

				// 1. 맨 왼쪽 칸
				if(j == 0) {
					dp[i][j] = dp[i - 1][j] + triangle[i][j];
				}

				// 2. 맨 오른쪽 칸
				else if(j == i) {
					dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
				}

				// 3. 그 외 나머지
				else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
				}
			}
		}

		int max = Integer.MIN_VALUE;

		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp.length; j++) {
				if(dp[i][j] > max) {
					max = dp[i][j];
				}
			}
		}

		System.out.println(max);

	}
}
