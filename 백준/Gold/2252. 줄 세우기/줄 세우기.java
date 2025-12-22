import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 위상 정렬은 무.조.건 인접리스트로 진입차수로 표현한다
		ArrayList<Integer>[] graph = new ArrayList[N + 1];

		// graph[A] = A 다음에 와야하는 학생들 목록

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// indegree[i] = 진입차수 배열(꼭 필수임)
		int[] indegree = new int[N + 1];

		// 1. 비교 정보 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			//A가 B보다 앞에 서야한다 --> 기준 방향으로 입력하기
			graph[A].add(B);

			// 진입차수 배열도 업데이트
			indegree[B]++;
		}

		// 2.진입차수가 0인 학생들을 담을 큐
		// 지금 당장 줄세워도 되는 것들
		Queue<Integer> q = new ArrayDeque<>();

		// 3. 처음엔 진입차수가 0인애들을 큐에 넣기
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}

		// 결과를 저장할 StringBuilder

		StringBuilder sb = new StringBuilder();

		// 4. 위상정렬 시작
		while(!q.isEmpty()) {

			// 현재 줄에 세울 학생
			int cur = q.poll();
			sb.append(cur).append(" ");

			// cur 다음 와야할 학생들 처리
			for(int next : graph[cur]) {

				// cur을 앞에 세웠으므로, next의 진입차수 1 감소
				indegree[next]--;

				// 이제 앞에 올 사람이 없어졌다면 큐에 추가
				if(indegree[next] == 0) {
					q.offer(next);
				}
			}
		}

		System.out.println(sb.toString());





	}
}
