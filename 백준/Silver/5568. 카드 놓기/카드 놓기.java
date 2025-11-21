import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 카드 n장을 놓음, 그중 k장을 선택함
	 * 조합이며, 순서가 중요하다, + 선택할 수 있는 개수도 정해져 있음
	 * 개수 = depth
	 * 순서가 중요하기 때문에, 원복이 필요할거 같고, visited 배열도 필요해 보인다
	 */
	static int N; // 카드의 전체 개수
	static int K; // 뽑을 카드 수

	static int[] cards;
	static boolean[] visited;
	static HashSet<String> numbers = new HashSet<>();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		cards = new int[N];
		visited = new boolean[N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cards[i] = Integer.parseInt(st.nextToken());
		}


		dfs(0, ""); // 빈 문자열 ㅇㅇ
		System.out.println(numbers.size());

	}

	static void dfs(int depth, String cur) {
		if(depth == K) {
			numbers.add(cur);
			return;
		}

		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, cur + cards[i]);
				visited[i] = false;
			}
		}
	}
}
