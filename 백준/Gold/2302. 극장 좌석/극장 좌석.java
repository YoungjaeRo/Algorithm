import java.io.*;
import java.util.*;

public class Main {
	static int N; // 좌석 수
	static int M; // 고정석의 개수


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		M = Integer.parseInt(br.readLine());

		int[] vip = new int[M];

		for(int i = 0; i < M; i++) {
			vip[i] = Integer.parseInt(br.readLine());
		}

		// ways[k] = k는 최대 N까지 필요함
		long[] ways = new long[N + 1];

		ways[0] = 1;

		if(N >= 1) {
			ways[1] = 1;
		}

		// dp 배열 초기화
		for(int i = 2; i <= N; i++) {
			ways[i] = ways[i - 1] + ways[i - 2];
		}

		/**
		 * VIP를 기준으로 구간 길이를 구해 곱해준다
		 */

		long answer = 1;
		int prev = 0;

		for(int i = 0; i < M; i++) {
			int curVip = vip[i];

			// VIP를 제외한 현재 구간의 길이
			int len = curVip - prev - 1;

			// 헤당 구구나 경우의 수를 곱합
			answer = answer * ways[len];
			prev = curVip;
		}

		// 마지막 VIP구간도 곱해줘야 한다.
		int lastLen = N - prev;
		answer = answer * ways[lastLen];
		System.out.println(answer);
	}
}
