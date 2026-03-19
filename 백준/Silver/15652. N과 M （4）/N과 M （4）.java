import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 순서가 중요하지 않으므로, 어찌보면 조합이다(근데 중복을 허용하는 ㅇㅇ)
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

		backtrack(0, 1);

		System.out.println(sb);
	}

	static void backtrack(int depth, int start) {

		if(depth == M) {
			for(int p : pick) {
				sb.append(p).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i <= N; i++) {
			pick[depth] = i;

			backtrack(depth + 1, i);
		}
	}
}
