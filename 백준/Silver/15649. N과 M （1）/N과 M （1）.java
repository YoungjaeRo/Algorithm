import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	static boolean[] visited;

	static int[] answer;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		answer = new int[M];

		backtrack(0);
		System.out.println(sb);
	}

	static void backtrack(int depth) {
		if(depth == M) {
			for(int p : answer) {
				sb.append(p).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 1; i <= N; i++) {
			// 아직 선택한적이 없는 숫자라면
			if(!visited[i]) {
				visited[i] = true;
				answer[depth] = i;

				backtrack(depth + 1);
				// 꼭 다음 선택지(리셋후 시작)을 위해 원복을 해줘야 한다
				visited[i] = false;

			}
		}


	}
}
