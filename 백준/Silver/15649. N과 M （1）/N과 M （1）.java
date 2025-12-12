import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	static int[] arr;
	static boolean[] visited;

	static int[] answer;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new boolean[N];

		answer = new int[M];

		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}

		Arrays.sort(arr);

		backtrack(0);
		System.out.println(sb);
	}

	static void backtrack(int depth) {
		if(depth == M) {
			for(int num : answer) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				answer[depth] = arr[i];

				backtrack(depth + 1);
				visited[i] = false;
			}
		}
	}
}
