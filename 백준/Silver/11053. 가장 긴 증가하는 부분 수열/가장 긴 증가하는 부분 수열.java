import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp;

	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new int[N];

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 길이 1로 초기화
		Arrays.fill(dp, 1);

		for(int i = 0; i < N; i++) {

			for(int j = 0; j < i; j++) {

				// 증가하는 수열 조건
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
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
