import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 같은 수를 여러번 골라도 된다
	 *
	 * 중복은 허용하지 않음
	 *
	 * 순서가 중요함 --> 순열 문제임
	 */
	static int N;
	static int M;

	static int[] pick;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		pick = new int[M];


		backtrack(0);

		System.out.println(sb);
	}

	static void backtrack(int depth) {

		// 길이 M만큼 다 골랐을 때
		if(depth == M) {
			for(int p : pick) {
				sb.append(p).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 1; i <=N; i++) {
			pick[depth] = i;
			backtrack(depth + 1);
		}
	}
}
