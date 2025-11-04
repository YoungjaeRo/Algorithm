import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 점화식 : dp[i] --> i번째 원소로 끝나는 증가 부분수열의 최대 길이
	 * dp[i] : 1 + Math.max(dp[j]) 단, i > j, 그리고 A[i] > A[j]
	 * 붙일게 없다면, dp[i] = A[i] 자기 자신만을 고른 경우 : 길이 1
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
		 * dp[i] = i번째 원소 A[i]로 끝나는 증가 부분수열의 최대 길이
		 */

		int[] dp = new int[N];
		Arrays.fill(dp, 1); // 최소 자기 자신 : 길이 1
		

		int answer = 1;

		for (int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				
				// 증가 조건을 충족한다면,
				if(A[j] < A[i]) {

					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}

			// 전체 최댓값 갱신
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}

