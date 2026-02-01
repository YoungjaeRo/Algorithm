import java.io.*;
import java.util.*;


public class Main {

	static final int MAX = 100000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// dist[x] = N에서 X까지의 최단시간(미방문이명 -1)
		int[] dist = new int[MAX + 1];
		Arrays.fill(dist, -1);

		//prev[x] = x에 오기 직전의 경로
		int[] prev = new int[MAX + 1];
		Arrays.fill(prev, -1);

		ArrayDeque<Integer> q = new ArrayDeque<>();

		// 시작점 세팅
		dist[N] = 0;
		q.offer(N);
		prev[N] = -1; // 시작점은 전의 경로는 -1로 설정

		while(!q.isEmpty()) {
			int cur = q.poll();

			// 도착점에 도착했다면 바로 답 출력하고 종료
			if(cur == K) {
				break;
			}

			// 같은 가중치에서 이동할 수 있는 옵션 3가지
			int[] nexts = {cur - 1, cur + 1, cur * 2};

			for(int next : nexts) {

				//1. 범위체크
				if(next < 0 || next > MAX) {
					continue;

				}

				// 2. 이미 방문했으면 스킵
				if(dist[next] != -1) {
					continue;
				}

				dist[next] = dist[cur] + 1;

				// 경로 복원
				prev[next] = cur;

				q.offer(next);
			}
		}

		//최단 시간 출력하기
		StringBuilder sb = new StringBuilder();
		sb.append(dist[K]).append('\n');

		// 경로 복원하기
		ArrayList<Integer> path = new ArrayList<>();
		// 도착점부터 시작
		int cur = K;

		while(cur != -1) {
			path.add(cur);
			cur = prev[cur];
		}

		// 현재 path는 K -> ... -> N (역순) 이니까 뒤집어서 출력
		Collections.reverse(path);

		for(int x : path) {
			sb.append(x).append(' ');
		}
		sb.append('\n');

		System.out.println(sb);
	}
}
