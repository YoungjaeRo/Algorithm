import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] arr; // i가 선택한 학생
	static boolean[] visited; // DFS 완전 종료 여부
	static boolean[] finished; // DFS 완전 종료 여부
	static int cycleCount; // 사이클에 포함된 학생의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			n = Integer.parseInt(br.readLine());

			arr = new int[n +1];
			visited = new boolean[n + 1];
			finished = new boolean[n + 1];
			cycleCount = 0;


			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// dfs 시작
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}

			sb.append(n - cycleCount).append("\n");
		}

		System.out.println(sb);
	}

	// 1 2 3 4 2 (사이클 : 2-3-4)

	static void dfs(int cur) {
		visited[cur] = true;

		int next = arr[cur];

		if(!visited[next]) {
			dfs(next);

			// 이미 방문은 완료했지만, 아직 끝이 나지 않은 상태 -- > 사이클 발견
		} else if(!finished[next]) {

			// 사이클 구성원 세기
			int temp = next;
			cycleCount++; // 사이클에 자기 자신을 포함

			// temp = 1, cur = 3
			while(temp != cur) {
				temp = arr[temp];
				cycleCount++;
			}

		}

		// 현재 노드 탐색 종료 표시
		finished[cur] = true;
	}
}
