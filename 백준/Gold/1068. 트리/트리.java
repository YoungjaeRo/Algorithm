import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 트리가 주어지고, 노드를 하나 지운다.
	 * 그때 남은 트리에서, 리프 노드의 개수를 구하는 프로그램을 작성해라
	 *
	 * 노드를 지우면, 그 노드와, 노드의 모든 자손이 트리에서 제거된다
	 */

	static int N; // 노드의 개수

	static List<Integer> [] parents; // 각 노드의 자식 목록 (인접 리스트)

	static int root; // 루드 노드 번호

	static int del; // 삭제할 노드 번호


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 입력 : 노드 개수
		N = Integer.parseInt(br.readLine());

		// 2. 인접 리스트
		parents = new ArrayList[N];

		for(int i = 0; i < N; i++) {
			parents[i] = new ArrayList<>();
		}


		// 부모 노드 입력받기
		// parent[i] = i번째 노드의 부모 노드
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			// 각 i번째들에 대한 부모
			int parent = Integer.parseInt(st.nextToken());

			// 부모가 -1이면, 해당 노드는 루트
			if(parent == -1) {
				root = i;

			} else {
				// 트리를 "부모 -> 자식" 방향 인접리스트로 구성
				// p의 자식으로 i를 추가
				parents[parent].add(i);
			}
		}

		// 삭제할 노드 번호 입력받기
		del = Integer.parseInt(br.readLine());

		//  루드 자체를 삭제하면, 전체 트리가 사라짐 -- > 리프 0개
		if(del == root) {
			System.out.println(0);
			return;
		}

		// 루트로부터 dfs를 실행해서 남아있는 리프 노드개수 구하기
		int leaf = dfsCountLeaves(root);
		System.out.println(leaf);

	}
	/**
	 * dfsCountLeaves(cur)
	 * - cur 노드를 루트로 하는 남아있는 서브트리에서 리프 노드 수를 반환한다.
	 *
	 * 여기서 중요한 규칙:
	 * - del 노드와 del의 모든 자식(서브트리)은 트리에서 없는 것 취급
	 * - 리프(leaf) = "삭제되지 않고 남아있는 자식이 0개"인 노드
	 */

	static int dfsCountLeaves(int cur) {
		// 1. 현재 노드가 삭제 대상이면, 이 노드 자체도 없고, 아래도 없음
		if(cur == del) {
			return 0;
		}

		// 2. cur의 자식 중, 삭제 되지 않고 남아있는 자식의 개수를 세기 위한 변수
		int aliveChild = 0;


		// 자식 서브트리에서 얻은 리프 개수들을 더할 변수
		int sumLeaves = 0;


		// 3. 현재 cur의 자식들을 전부 확인하기
		for(int next : parents[cur]) {
			// 자식의 del 대상이라면, 그 자식의 서브트리는 통째로 사라진다. (없는 취급)
			if (next == del) {
				continue;
			}

			// 삭제 대상이 아닌 자식이라면, 살아있는 자식 카운트 증가
			aliveChild++;

			// 또 그 자식 서브트리에서 리프 개수를 재귀적으로 더하기
			sumLeaves = sumLeaves + dfsCountLeaves(next);
		}

		// 삭제 반영 후, 살아있는 자식이 0명이면 cur은 리프!
		if(aliveChild == 0) {
			return 1;
		}

		// 살아있는 자식이 있다면, 자식들이 만든 리프 개수 합을 그대로 반환
		return sumLeaves;
	}
}
