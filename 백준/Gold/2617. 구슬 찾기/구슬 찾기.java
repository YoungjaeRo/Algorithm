import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 모양은 같으나, 무게가 모두 다른 N개의 구술중 무게가 전체의 중간이 될수 없는 구슬의 개수를 구하시오
	 *
	 * 중간 지검은 (N + 1) / 2 번째에 위치해야한다
	 *
	 * 그럼 이 중간 지점보다, 더 많은 개수로 가볍거나, 무거운 구술이 내 앞에 있거나 한다면, 그것은 중간 무게의 구슬이 될수 없다
	 *
	 * 아이디어 :  해당 문제를 그래프로 푼다고 하면, 그러면,
	 * 각 구슬 i에 대해서,
	 * i 보다 무거운 구슬 수
	 * i 보다 가벼운 구슬 수
	 * 둘 중 하나라고 N / 2 를 초과하면, 해당 i 번째 구슬은 절대 중간이 될 수 없다
	 *
	 * 1) lighterThan[x] : x보다 "가벼운" 구슬들로 가는 간선 (x -> y, x > y)
	 * 2) heavierThan[x] : x보다 "무거운" 구슬들로 가는 간선 (x -> y, x < y)
	 *
	 * 예) a > b 라면
	 * - b는 a보다 가볍다  => lighterThan[a].add(b)
	 * - a는 b보다 무겁다  => heavierThan[b].add(a)
	 */

	static int N; // 구슬의 개수
	static int M; // 무게를 비교하게 될 쌍

	static ArrayList<Integer> [] lighterThan;
	static ArrayList<Integer> [] heavierThan;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());


		lighterThan = new ArrayList[N + 1];
		heavierThan = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			lighterThan[i] = new ArrayList<>();
			heavierThan[i] = new ArrayList<>();

		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // a 가 b보다 무겁다 ㅇㅇ

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			lighterThan[a].add(b); // a 보다 가벼운 b
			heavierThan[b].add(a); // b 보다 무거운 a
		}

		int half = (N + 1) / 2;
		int answer = 0;

		// 각 구슬 i에 대해서, 중간 지점이 될 수 있는지 없는지 검증
		for(int i = 1; i <= N; i++) {

			// i 보다 가벼운 구슬이 half개의 초과면, 절대로 중간이 불가하다
			int lighterCount = bfsCount(i, lighterThan);

			if(lighterCount >= half) {
				answer++;
				continue; // 이미 한쪽이 half를 초과했기 때문에, 새로운 구슬을 탐색해도 된다.
			}

			int heavierCount = bfsCount(i, heavierThan);
			if(heavierCount >= half) {
				answer++;
			}
		}

		System.out.println(answer);

	}

	static int bfsCount(int start, List<Integer> [] list) {
		boolean[] visited = new boolean[N + 1];

		Queue<Integer> q = new ArrayDeque<>();

		// 시작점 방문처리 꼭 해주기
		visited[start] = true;
		q.add(start);

		int count = 0;

		while(!q.isEmpty()) {
			int cur = q.poll();


			for(int next : list[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					count++;
					q.add(next);
				}
			}
		}

		return count;
	}
}
