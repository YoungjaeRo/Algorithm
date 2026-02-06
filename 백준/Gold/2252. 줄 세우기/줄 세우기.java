import java.io.*;
import java.util.*;

public class Main {

	static int N; // 학생 수

	static int M; // 키를 비교한 횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 위상 정렬용 인접리스트  & indegree 배열
		List<Integer>[] graph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int[] indegree = new int[N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());

			graph[front].add(back);
			indegree[back]++;
		}

		// PQ 선언
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// indegree가 0인것들을  큐에 삽입하기
		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder();

		// 인접행렬 시작
		while(!pq.isEmpty()) {

			int cur = pq.poll();

			sb.append(cur).append(" ");

			for(int next : graph[cur]) {
				// cur을 앞에 세웠으므로 next의 진입배열은 1 감소
				indegree[next]--;

				// 이제 더 이상 앞에 올사람이 없으면, 큐에 삽입
				if(indegree[next] == 0) {
					pq.offer(next);
				}
			}
		}

		System.out.println(sb);
	}
}
