import java.util.Scanner;

public class Main {
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 원소의 개수
		int M = sc.nextInt(); // 질의의 개수

		parent = new int[N + 1]; // 대표 노드 값 저장 배열

		for(int i = 0; i < N + 1; i++) {
			parent[i] = i; //  대표노드 값 저장 배열 초기화
		}

		for(int i = 0; i < M; i++) {
			int question = sc.nextInt(); // union 0 인지 checkSame 1인지 입력받음
			int a = sc.nextInt();
			int b = sc.nextInt();

			if(question == 0) {// 0, union 연산이라면 (대표 노드끼리 같은 집합인지 판별하고 이어주는 연산 ==> 이어줄때, 꼭 대표노드들을 찾아서 연결해주어야한다.)
			union(a,b);
		} else { // 1, 두 원소의 대표값이 같은지 보기
			boolean result = checkSame(a, b);
			if(result == true) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
				}
			}

		}
	}
	public static void union(int a, int b) {
		// 무작정 저 둘을 연결하는것이 아니라, 대표 노드를 찾은다음, 연결을 진행
		/**
		 * 이쪽 부분 중요 꼭 각각의 대표 노드들을 먼저 찾은 다음, 연결해줌
		 */
		a = find(a);
		b = find(b);
		if(a != b) { // 해당 두 노드의 대표노드가 서로 다르다면,
			parent[b] = a; // 한쪽의 대표노드값을 다른한쪽에 넣어줌 (연결) (작은값을)
		}
	}

	public static int find(int a) {
		if (a == parent[a]) { // 해당 a와 대표노드의 인덱스 값이 이미 같다면 그냥 그 해당 값을 추출
			return a;
		} else {
			/**
			 *  이쪽 부분 중요
			 */
			return parent[a] = find(parent[a]); // 해당 a의 인덱스값이 다르면, 그 a는 대표 노드가 아니므로 해당 a인덱스에 담겨진 값을 기준으로 다시 탐색한다. 그리고 가장 대표 노들까지 가면, 해당 경로 거쳐온 노드의 인덱스 값을 대표값으로 바꿔준다. ==> 이걸 "경로압축" 이라고 한다
		}
	}
	public static boolean checkSame(int a, int b) {// 각각의 대표노드 값들 추출하고 비교
		 a = find(a);
		 b = find(b);
		 if(a == b) {
			 return true;
		 } else {
			 return false;
		 }
	}
}
