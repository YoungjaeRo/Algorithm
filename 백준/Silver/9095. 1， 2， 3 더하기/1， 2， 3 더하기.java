import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static int[] arr;
	static int[] dp;

	public static void main(String args[]) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	StringBuilder sb = new StringBuilder();

	T = Integer.parseInt(br.readLine());

	arr = new int[T];

	dp = new int[12]; // 정수는 최대 11까지 주어짐

	for(int i = 0; i < T; i++) {
		int num = Integer.parseInt(br.readLine());
		arr[i] = num;
	}

		/**
		 * dp[i]는 1,2,3으로 i를 만들 수 있는 경우의 수
		 * dp[i] = dp[i -1] + dp[i-2] + dp[i-3]
		 */

		// 기저값들 세팅하기
		dp[1] = 1;
		dp[2] = 2;
		dp[3] =4;

		for(int i = 4; i <= 11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}

		//각 테스트 케이스마다 결과 출력하기
		for(int num : arr) {
			sb.append(dp[num]).append("\n");
		}

		System.out.println(sb);

	}
}
