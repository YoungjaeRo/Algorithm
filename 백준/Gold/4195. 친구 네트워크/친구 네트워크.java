import java.io.*;
import java.util.*;

public class Main {
	static int T; // 테스트케이스 수
	static int F; // 친구 관계의 수

	// 유니온 파인드 용 변수 선언
	static int[] parent;


	static int[] size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder out = new StringBuilder();

			while(T-- > 0) { // 테케 만큼 실행
				F = Integer.parseInt(br.readLine().trim());

				// 친구 관계는 F, 줄당 2명씩, 사람수는 최대 2F
				int max = 2 * F;

				parent = new int[max];
				size = new int[max];

				// 각 배열을 자기 자신을 부모로, 사이즈는 1로 초기화
				for(int i = 0; i < max; i++) {
					parent[i] = i;
					size[i] = 1;
				}

				// 사람들의 이름, ID 를 기준으로 저장하기
				Map<String, Integer> map = new HashMap<>();
				int id = 0;

				for(int i = 0; i < F; i++) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					String p1 = st.nextToken();
					String p2 = st.nextToken();

					// p1 이 처음 등장하면 ID 부여
					if(!map.containsKey(p1)) {
						map.put(p1, id++);
					}

					// p2 가 처음 등장하면 ID 부여
					if(!map.containsKey(p2)) {
						map.put(p2, id++);
					}

					// 이제 각 이름에 대한 아이디값 추출
					int a = map.get(p1);
					int b = map.get(p2);

					int root = union(a, b);
					out.append(size[root]).append("\n");

				}
				
			}
			
		System.out.println(out);

	}

	static int find(int x) {

		if(parent[x] == x) {

			return parent[x];
		}

		// 경로 압축
		parent[x] = find(parent[x]);

		return parent[x];
	}

	static int union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		// 둘의 부모 노드가 같으면, 둘중 하나의 부모노드를 출력함
		if(ra == rb) {
			return ra;
		}

		// 둘의 부모 노드가 다르면, (ra) 한쪽으로 통일
		parent[rb] = ra;

		// size도 (ra) 기준 업데이트
		size[ra] = size[ra] + size[rb];

		return ra;

	}

}
