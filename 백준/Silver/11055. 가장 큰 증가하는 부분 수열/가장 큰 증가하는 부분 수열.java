import java.util.*;
import java.io.*;


public class Main {
	/**
	 * 점화식 : dp[i] --> i번째 원소로 끝나는 증가 부분수열의 최대합
	 * dp[i] : A[i] + Math.max(dp[j]) 단, i > j, 그리고 A[i] > A[j]
	 * 붙일게 없다면, dp[i] = A[i] 자기 자신만을 고른 경우
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N]; // 각 배열의 위치와 숫자를 저장할 배열 생성

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());

		}

		/**
		 * dp[i] = i번째 원소 A[i]로 끝나는 증가 부분수열의 최대의 합
		 */

		int[] dp = new int[N];

		int answer = 0;

		for (int i = 0; i < N; i++) {
			// 촤소한 자기 자신하나로 수열을 만든다
			dp[i] = A[i];


			// i 앞쪽에서 A[i] 값보다 작은 값들만 연결함
			for(int j = 0; j < i; j++) {
				if(A[j] < A[i]) {
					// j로 끝나는 최댓합에 A[i]를 붙여준다

					dp[i] = Math.max(dp[i], dp[j] + A[i]);
				}
			}

			// 전체 최댓값 갱신
			if(dp[i] > answer) {
				answer = dp[i];
			}
		}
		System.out.println(answer);
	}
}

