import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 지민이는 거짓말쟁이가 되기 싫다
	 * 몇몇 사람들은 그 이야기의 진실을 안다
	 *
	 * 그러한 사람들이 파티에 왔을때, 지민이는 진실을 얘기할 수 밖에 없다
	 *
	 * 어떤 사람이 어떤 파티에서는 진실을 듣고, 또 어떤 파티에서는 과장된 이야기를 들었으면, 지민이는 거짓말쟁이가 된다
	 */

	static int N; // 사람 수
	static int M; // 파티 수

	static int[] parents;

	static int find(int x) {

		// 해당 원소와 부모 노드가 같으면

		if(x == parents[x]) {
			return parents[x];
		}

		// 다르면,

		// 경로 단축
		parents[x] = find(parents[x]);

		return parents[x];
	}

	static void union(int x, int y) {
		int rx = find(x);
		int ry = find(y);

		// 둘의 부모가 같으면
		if(rx == ry) {

			// 이미 같은 집합이면 할거 없음
			return;
		} else {
			// 한쪽으로 부모노드 통일
			parents[ry] = rx;
		}
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 유니온 파인드 초기화
		parents = new int[N + 1];

		// 처음에는 각자 자기자신을 대표노드로 둔다
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}


		// 진실을 알고있는 사람들 정보 저장
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		int[] truthPeople = new int[t];

		for(int i = 0; i < t; i++) {
			truthPeople[i] = Integer.parseInt(st.nextToken());
		}

		// 파티에 오는 사람들의 수, 번호
		// 파티 참석자 목록 저장 (인접리스트)
		// parties[i] = i 번째 파티에 참석한 사람들

		ArrayList<Integer>[] parties = new ArrayList[M];

		for(int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
		}

		// 파티 입력 받고, 같은 파티 사람들을 union으로 묶기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			// 파티에 참석한 사람 수
			int k = Integer.parseInt(st.nextToken());

			// 참석한 사람 수 만큼 해당 i번째 리스ㅌ에 참석자들 입력함
			for(int j = 0; j < k; j++) {
				parties[i].add(Integer.parseInt(st.nextToken()));
			}

			/**
			 * 핵심: 같은 파티에 있던 사람들은 "진실이 전파될 수 있는 관계"가 생긴다.
			 * 그래서 한 파티 내부의 사람들을 전부 같은 집합으로 묶는다.
			 *
			 * 가장 직관적인 묶기(사슬 연결):
			 * party = [p0, p1, p2, p3] 이면
			 * union(p0, p1), union(p1, p2), union(p2, p3)
			 *
			 * 이렇게만 해도 결국 p0~p3 전부 하나의 집합이 된다.
			 */

			for(int j = 1; j < parties[i].size(); j++) {
				int prev = parties[i].get(j - 1);
				int cur = parties[i].get(j);
				union(prev, cur);
			}
		}

		/**
		 * 집합은 완성시켰고, 결국 진실을 아는사람과 파티에 동석한 사람들으 다 진실을 알게되었다
		 *
		 * 각 파티에 대해서, 한명이라도 진실의 집합이면 --> 거짓말 불가
		 * 아무도 진실의 집합이 아니라면 -- > 거짓말 가능
		 */

		int answer = 0;

		for(int i = 0; i < M; i++) {
			boolean canLie = true;


			// i번째 참석자들을 확인
			for(int person : parties[i]) {
				int personRoot = find(person);

				// 진실을 아는 사람 중 하나라도 같은 집합이면, 바로 거짓말 불가능

				for(int tp : truthPeople) {
					int rootTp = find(tp);

					if(rootTp == personRoot) {
						canLie = false;
						break;
					}
				}

				if(canLie == false) {
					break; // 이미 불가 확정이면, 더 볼 필요가 없다
				}
			}

			if(canLie == true) {
				answer++;
			}
		}

		System.out.println(answer);

	}
}
