import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int K;
	static int X;

	static int[] visited;
	static ArrayList<Integer>[] A;
	static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		/**
		 * 문제 분석
		 * 1. 도로의 거리가 다 1로 통일이 되었기 때문에, 가중치가 없는 그래프이다
		 * N  = 도시의 게수 (노드)
		 * M = 도로의 개수 (엣지)
		 * K = 거리 정보 (가중치)
		 * X = 출발도시의 정보 (시작할 노드 인덱스)
		 */

		// 그래프 문제는 대부분 인접리스트로 풀면 된다, 또한 해당 X로 가는 최단거리를 구하는 문제이기 때문에, BFS 알고리즘을 사용해야 한다.
		/**
		 * 1. BFS 탐색 알고리즘으로 탐색을 수행하면서 각 도시로 가는 최단 거리값을 방문 배열에 저장한다.
		 * 최초에는 방문도시가 1이고, 이동하지 않았으므로, 1의 방문배열에 0을 저장한다.
		 * 이후 방문하는 도시의 방문 배열값에는, 이전 도시의 방분 배열 값  + 1 저장하는 방식으로 이동거리를 저장한다.
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());

		 N = Integer.parseInt(st.nextToken()); // 도시의 개수
		 M = Integer.parseInt(st.nextToken()); // 도로의 개수
		 K = Integer.parseInt(st.nextToken()); // 거리의 정보
		 X = Integer.parseInt(st.nextToken()); // 출발도시의 정보

		// 그래프 데이터 저장 인접 리스트
		A = new ArrayList[N + 1]; // 0번 인덱스는 헷갈려서 안쓸 예정

		// 정답 노드들을 저장할 리스트 초기화
		answer = new ArrayList<>();

		for(int i = 1; i <= N; i++) { // 도시(노드)의 개수만큼 반복
			A[i] = new ArrayList<Integer>(); // A[1] ~ [4] 까지 ArrayList로 초기화 해준다.
		}

		for(int i = 1; i <= M; i++) { // 도로(엣지)의 개수만큼 반복
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 인접리스트에 그래프 정보 주입
			A[start].add(end);
		}

		// 방문거리 저장 배열 선언
		visited = new int[N + 1];

		// 방문 배열 초기화 하기
		for(int i = 0; i <= N; i++) { // 방문 값을 다 0으로 초기화
			visited[i] = -1;
		}

		BFS(X); // 시작 노드로부터 BFS 실행 시작
		for(int i = 0; i <= N; i++) {
			if(visited[i] == K) { // 해당 K만큼(거리) 의 이동크기 인덱스 값이 나온다면
				answer.add(i); // 정답 배열에 해당 i번째 노드 입력
			}
		}

		if(answer.isEmpty()) { // K만큼의 크기와 딱 떨어지는 이동거리의 노드가 없다면 (정답 배열에 아무런 정답이 없다면) -1을 출력
			System.out.println("-1");
		} else {
			Collections.sort(answer); // answer 배열에 들어있는 답 노드들을 오름 차순으로 정렬
			for(int ans : answer) {
				System.out.println(ans); // 답 출력
			}
		}
	}
	public static void BFS(int Node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(Node); // 큐에 현재 노드를 삽입

		visited[Node]++; // 방문배열 값 + 1

		while(!queue.isEmpty()) { // 큐가 비어있을때까지 반복 실행
			int now_Node = queue.poll();
			for (int i : A[now_Node]) {
				if(visited[i] == -1) { // 아직 방문하지 않은 노드라면,,
					visited[i] = visited[now_Node] + 1;
					queue.add(i); // 큐에 다시 넣기
				}
			}
		}

	}
}
