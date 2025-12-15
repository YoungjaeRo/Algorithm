import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N이 2만개까지 들어오기 때문에, 일단 플로이드 워셜은 불가함
	 * N이 2만개까지 들어오기 때문에, 플로이드 워셜 불가
	 * 가중치 없음(=모든 간선 비용 1), 최단거리 -> BFS
	 *
	 *  헛간의 개수는 N개이다,
	 *  수현이는 재석이를 1번 헛간부터 찾는다
	 *  모든 헛간은 M개의 양방향의 길로 이어져 있고
	 *  그 양 끝을 A-i와 B-i로 나타낸다
	 */

	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 노드의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수

		ArrayList<Integer> [] list = new ArrayList[N + 1];

		// 1. 인접리스트 생성하기
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();

		}

		// 2. 간선의 횟수 만큼 간선 정보 입력해주기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 이렇게 양 방향으로 넣어주어야 한다.

			list[start].add(end);
			list[end].add(start);

		}

		// BFS 준비 하기
		/**
		 * dist[i] = 1번 헛간부터 i 번 헛간까지의 최소이동 거리
		 */

		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];

		dist[1] = 0;
		visited[1] = true;

		Queue<Integer> q = new LinkedList<>();

		q.add(1); // 시작점 입력해주기

		while(!q.isEmpty()) {
			int cur = q.poll();

			// 해당 리스트 안에 들어 있는 값들을 탐색함
			for(int nxt : list[cur]) {
				if(!visited[nxt]) {
					visited[nxt] = true;
					dist[nxt] = dist[cur] + 1;
					q.add(nxt);
				}
			}
		}

		// 정답 출력 (가장 먼 거리, 그 헛간의 번호(거리 값이 같으면, 제일 작은 숫자로), 해당 헛간과 같은 거리를 가지고 있는 헛간의 수)

		// 가장 먼거리
		int maxDist = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			if(dist[i] > maxDist) {
				maxDist = dist[i];
			}
		}

		// 해당 가장 먼거리를 가지고 있는 헛간의 인데스 구하기
		int minIdx = Integer.MAX_VALUE;
		int count = 0;

		for(int i = 1; i <= N; i++) {
			if(dist[i] == maxDist) {
				count++;
				minIdx = Math.min(minIdx, i);
			}
		}

		System.out.println(minIdx + " " + maxDist + " " + count);





	}
}
