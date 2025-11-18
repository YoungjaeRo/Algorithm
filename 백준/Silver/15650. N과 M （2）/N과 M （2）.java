import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		backtrack(0, 1);
        System.out.println(sb);
	}

	static void backtrack(int depth, int start) {
			// 1. 종료조건
		if(depth == M) {
			for(int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i <= N; i++) {
			arr[depth] = i;
			backtrack(depth + 1, i + 1); // i보다 큰 애들만 탐색
		}
	}
}
