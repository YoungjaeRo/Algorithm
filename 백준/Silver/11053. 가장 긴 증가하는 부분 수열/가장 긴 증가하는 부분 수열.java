

import java.io.*;
import java.util.*;


public class Main {
	/**
	 * 해당 문제를 dp로 푼다고 할때 점화식을 어떻게 세우는 게 좋을까
	 * dp[i] = i가 가장 마지막 순서인 길이
	 */

	static int A;
	static int[] arr ; // 숫자 저장용 배열
	static int[] dp; // dp 용 배열임

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		A = Integer.parseInt(br.readLine());
		arr = new int[A];
		dp = new int[A];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		/**
		 * dp 시작
		 */

		// 1. 일단은 자기자신(길이 1)로 초기화 해줌
		
		Arrays.fill(dp, 1);
		
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < i; j++) {
				
				if(arr[j] < arr[i]) { // 수열 증가 조건을 충족한다면,
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int answer = 1;
		
		for(int num : dp) {
			if(num > answer) {
				answer = num;
			}
		}
		System.out.println(answer);
	}
}
