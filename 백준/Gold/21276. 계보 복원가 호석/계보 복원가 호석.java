import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		String[] names = new String[N];
		StringTokenizer ss = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			names[i] = ss.nextToken();
		}

		//이름 오름차순 정렬
		Arrays.sort(names);

		// 이름과 인덱스를 매핑
		Map<String, Integer> map = new HashMap<>();

		for(int i = 0; i < N; i++) {
			String name = names[i];
			map.put(name, i);
		}

		int M = Integer.parseInt(br.readLine());

		// 조상-- > 자손 방향 인접리스트 그래프
		List<Integer>[] graph = new ArrayList[N];

		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		int[] indegree = new int[N];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();

			int c = map.get(child);
			int p = map.get(parent);

			graph[p].add(c);
			indegree[c]++;
		}

		// 각 사람의 직계자식만 저장해놓을 리스트
		List<Integer> [] childrens = new ArrayList[N];

		for(int i = 0; i < N; i++) {
			childrens[i] = new ArrayList<>();
		}


		// 중요 : 위상정렬을 위한 PQ
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 시조(root) 목록
		List<Integer> roots = new ArrayList<>();

		// indegree가 0이며, 시조
		for(int i = 0; i < N; i++) {
			if(indegree[i] == 0) {
				roots.add(i);

				// PQ 에도 넣어주기
				pq.add(i);
			}
		}

		// 위상정렬용 indegree 복사본
		int[] curIndegree = Arrays.copyOf(indegree, indegree.length);

		/**
		 * 위상정렬 진행
		 *
		 * 핵심 로직:
		 * - parent를 하나 꺼내서
		 * - parent의 자손 child들의 indegree를 감소
		 * - child의 indegree가 0이 되는 "그 순간"
		 *   → parent가 child의 직계 부모
		 */

		while(!pq.isEmpty()) {
			int parent = pq.poll();

			for(int child : graph[parent]) {
				curIndegree[child]--;


				// 모든 조상이 처리된 순간
				if(curIndegree[child] == 0) {
					childrens[parent].add(child); // 직계 자식 확정
					pq.add(child);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		// 1. 시조의 수
		sb.append(roots.size()).append("\n");

		// 2. 시조 이름 (사전순)
		Collections.sort(roots);

		for (int i = 0; i < roots.size(); i++) {
			if (i > 0) sb.append(" ");
			sb.append(names[roots.get(i)]);
		}
		sb.append("\n");

		// 3. 사람별 이름 + 직계 자식 수 + 자식 목록
		for(int i = 0; i < N; i++) {
			Collections.sort(childrens[i]); // 자식도 사전 순 출력

			sb.append(names[i]).append(" ").append(childrens[i].size());

			for(int c : childrens[i]) {
				sb.append(" ").append(names[c]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
