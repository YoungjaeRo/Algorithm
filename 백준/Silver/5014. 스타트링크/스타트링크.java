import java.io.*;
import java.util.*;

public class Main {

	static int F; // 건물 총 층수
	static int S; // 강호의 시작 위치
	static int G; // 스타트링크의 위치
	static int U; // 한번에 올라가는 칸수
	static int D; // 한번에 내려가는 칸수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());


		int[] click = new int[F + 1];
		Arrays.fill(click, -1);

		click[S] = 0;

		// BFS 시작
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(S);

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur == G) {
				System.out.println(click[cur]);
				return;
			}

			int[] options = {cur + U, cur - D};

			for(int next : options) {
				// 범위 검증부터
				if(next < 1 || next > F) {
					continue;
				}

				// 이미 방문한적이 있어도 스킵
				if(click[next] != -1) {
					continue;
				}

				click[next] = click[cur] + 1;
				q.offer(next);
			}
		}

		System.out.println("use the stairs");
	}
}
