import java.util.Scanner;

public class Main {
	public static int[] parent; // 도시들을 저장해놓을 1차원 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 도시의 수
		int M = sc.nextInt(); // 여행계획에 속한 도시의 수

		int[][] city = new int[N + 1][N + 1]; // 도시들끼리의 연결된 것을 저장할 2차원 배열
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				city[i][j] = sc.nextInt(); // 연결된 도시 데이터 주입

			}
		}

		// 여행경로(1 > 2 > 3)을 저장할 배열
		int[] route = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			route[i] = sc.nextInt();
		}

		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) { // 일단은, 각자의 대표노드를 자기 자신으로 초기화
			parent[i] = i;
		}


		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++){
				if(city[i][j] == 1) { // 1, 즉 해당 도시들이 연결되어 있으면, union(대표노드들끼리 이어주는) 연산을 실행
					union(i,j);
				}
			}
		}

		//여행 계획 도시들이, 1개의 대표 도시로 연결되어 있는지 확인하기
		int index = find(route[1]);
		for(int i = 2; i < route.length; i++) {
			if(index != find(route[i])) {
				System.out.println("NO");
				return;
			}

		}
		System.out.println("YES");
	}
	public static void union(int i, int j) {
		i = find(i);
		j = find(j);

		if(i != j) {
			parent[j] = i;
		}
	}

	public static int find(int i) {
		if(parent[i] == i) {
			return i;
		} else {
			return parent[i] = find(parent[i]); // 재귀 함수의 형태로 구현함, 그리고 경로 압축을 해줌
		}
	}

}
