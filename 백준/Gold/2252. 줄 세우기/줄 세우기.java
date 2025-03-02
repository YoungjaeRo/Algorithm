import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main { // 정답이 여러가지 일 경우, 그냥 그 답을 출력? --> 답이 여러개가 나올 수 있음 --> 위상정렬 알고리즘
	public static void main(String[] args) {
		// 1. 위상정렬 알고리즘을 사용할 것이므로, 그래프 데이터를 바탕으로 인접리스트 (ArrayList<>[]를 생성함) & 진입차수 배열도 생성함
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt(); // 학생수
		int M = scanner.nextInt(); // 비교 횟수

		// 인접리스트 초기화
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		for(int i = 0; i<= N; i++) {
			A.add(new ArrayList<>());
		}

		// 진입차수 배열 생성
		int[] indegree = new int[N + 1];

        // 인접리스트에 데이터 저장하기
		for(int i = 0; i < M; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();

			A.get(start).add(end); // 해당 인접리스트에 데이터 저장하기 ex 4 --> 2
			indegree[end]++; // 4가 2를 가리키므로, 2의 진입차수 배열에 1 추가
		}

		// 위상정렬 시작
		// 1. 큐를 만들어주기
		Queue<Integer> queue = new LinkedList<>();

		// 2. 진입차수 탐색
		for(int i = 1; i<= N; i++) {
			if(indegree[i] == 0) { // 진입차수가 0인 노드를 큐에 삽입함
				queue.offer(i);
			}
		}

		// 3. 큐에 아무것도 없을때까지 실행함
		while (!queue.isEmpty()) {
			int now = queue.poll(); // 현재 큐에 담겨있는 노드를 빼서 정답으로 출력함
			System.out.println(now + " ");
			for(int next : A.get(now)) {
				indegree[next]--;
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

	}
}