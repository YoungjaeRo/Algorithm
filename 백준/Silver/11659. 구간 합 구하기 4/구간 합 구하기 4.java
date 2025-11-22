import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		/**
		 * 수 N까지 주어졌을때, i번째부터 j까지수의 합을 구하시오
		 * 누적합 배열을 만들어야함 prefix[i] = prefix[i-1] + arr[i];
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 누적합 배열
		int[] prefix = new int[N + 1]; // (1-base) 둔다.

		st = new StringTokenizer(br.readLine());

		// 누적합 배열 선언 완료

		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i-1] + num;
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			int answer = prefix[end] - prefix[start - 1];
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());

	}
}
