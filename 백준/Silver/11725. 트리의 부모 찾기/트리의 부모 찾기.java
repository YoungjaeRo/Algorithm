import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 코테에서 이렇게 생각하면 된다
	 *
	 * DFS / BFS 쓴다
	 *
	 * 그래프다
	 *
	 * visited 무조건 깔고 시작, 그게 안전함
	 */
	static int N;

	// 인접리스트
	static ArrayList<Integer>[] tree;

	// parent[i] = i의 부모
	static int[] parent;

	// DFS / BFS , 그래프 용 방문 배열
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		// 그래프 용 인접리스트 선언
		tree = new ArrayList[N + 1];

		parent = new int[N+ 1];

		visited = new boolean[N + 1];


		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		// 간선은 총 N - 1개
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 무방향그래프는 양방향으로 삽입해주기
			tree[a].add(b);
			tree[b].add(a);
		}

		// 루트 1번부터 시작
		dfsForTree(1);
		
		// 2번부터 N까지 부모 출력하기
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}

		System.out.println(sb);

	}

	static void dfsForTree(int cur) {
		// 현재 노드 방문처리
		 visited[cur] = true;

		 for(int next : tree[cur]) {
			 // 방문한적이 있으면, 건너 뜀
			 if(visited[next]) {
				 continue;
			 }

			 // next는 cur을 통해 처음 들어왔으니까, cur이 next의 부모가 된다
			 parent[next] = cur;

			 dfsForTree(next);
		 }
	}
}
