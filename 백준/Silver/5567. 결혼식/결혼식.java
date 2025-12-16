import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 상근이 부터 시작해서, 친구의 친구 즉, 인접리스트를 통해서 풀면된다 ㅇㅇ
	 */

	static int N;

	static int M;

	static List<Integer> [] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];

		// 인접리스트 초기화
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 친구 관계 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		// 1번부터 i까지 동기들에게 닿는데, 걸리는 거리 여기서 1 또는 2까지로 제한해야한다.

		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		// BFS 시작
		Queue<Integer> q = new ArrayDeque<>();

		int start = 1; // 1번째 동기부터 탐색 시작

		dist[start] = 0; // 시작점 거리 0

		q.add(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(dist[cur] == 2) {  // cur이 이미 2촌이면 여기서 더 퍼지면 3촌이 생김 -> stop
				continue;
			}

			for(int next : list[cur]) {
				if(dist[next] == -1) { // 한번도 방문한적이 없다면,
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}

		int answer = 0;
		for(int i = 2; i <= N; i++) {
			if(dist[i] == 1 || dist[i] == 2) {
				answer++;
			}
		}

		System.out.println(answer);

	}
}
