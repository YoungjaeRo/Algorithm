import java.io.*;
import java.util.*;

public class Main {

	static final int MAX = 100001;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

	int N = Integer.parseInt(st.nextToken());
	int K = Integer.parseInt(st.nextToken());

	int[] time = new int[MAX];
	boolean[] visited = new boolean[MAX];

	Queue<Integer> q = new LinkedList<>();
	visited[N] = true;
	time[N] = 0;

	q.offer(N);

	while(!q.isEmpty()) {
		int cur = q.poll();

		if(cur == K) {
			System.out.println(time[cur]);
			break;
		}

		int[] options = {cur - 1, cur + 1, cur * 2};

		for(int next : options) {

			// 1. 범위 밖이라면
			if(next < 0 || next >= MAX) {
				continue;
			}

			// 2. 이미 방문을 했다면,
			if(visited[next]) {
				continue;
			}

			// 3. 방문 처리
			visited[next] = true;
			
			// 4. 시간 업데이트
			time[next] = time[cur] + 1;
			
			// 5. 큐 삽입
			q.offer(next);
			}
		}
	}
}
