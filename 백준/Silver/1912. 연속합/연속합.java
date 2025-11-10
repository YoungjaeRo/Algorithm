import java.util.*;
import java.io.*;


public class Main {
	/**
	 * dp[i] = i 번째로 끝나는 연속 부분합의 최댓값
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] a = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());

		}

		// cur: 현재 위치에서 "끝나는" 연속합 최댓값
		// best: 지금까지 본 연속합 최댓값

		int cur = a[0];
		int best = a[0];

		for(int i = 1; i < N; i++) {
			//  이전 합에 이어 붙일지, 새로 시작할지 선택함
			cur = Math.max(a[i], cur + a[i]);
			best = Math.max(cur, best);

		}

		System.out.println(best);
	}
}
