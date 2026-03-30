import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static int[] numbers;

	static boolean[] visited;

	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		numbers = new int[N + 1];

		for(int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}

		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];

			dfs(i, i);
		}

		Collections.sort(answer);

		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");

		for(int a : answer) {
			sb.append(a).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int start, int cur) {
		visited[cur] = true;

		int next = numbers[cur];

		if(!visited[next]) {
			dfs(start, next);
		} else {
			if (start == next) {
				answer.add(start);
			}
		}
	}
}
