import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 해당 문제를 dp라고 생각했을때, dp[i] = i까지 포함된, 증가하는 수열의 가장 큰값
	 *
	 */

	static int A; // 수열의 크기
	static int[] arr; // 숫자를 저장해놓을 배열
	static int[] dp; // dp전용 배열


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		A = Integer.parseInt(br.readLine());

		arr = new int[A];
		dp = new int[A];

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열에 숫자 정보 저장
		for(int i = 0; i < A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		/**
		 * dp[i] = i까지 포함한, 증가하는 부분수열의 최대값
		 */

		for(int i = 0; i < A; i++) {

			//일단은 본인의 arr[i]에 대응하는 값들로 세팅해준다
			dp[i] = arr[i];

			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) { // 앞에 있는 값보다 더 크다면 --> 증가하는 수열의 조건
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
		}

		int answer = 0;
		for(int i = 0; i < A; i++) {
			if(dp[i] > answer) {
				answer = dp[i];
			}
		}

		System.out.println(answer);

	}
}
