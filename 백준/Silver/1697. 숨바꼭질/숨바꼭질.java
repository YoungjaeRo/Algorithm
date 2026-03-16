import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int K;

	static final int size = 100001;



	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 해당 거리까지 걸리는 시간을 저장해놓을 배열 선언
		int[] time = new int[size];

		// 각 걸리는 시간을 -1로 초기화
		Arrays.fill(time, -1);

		// 수빈이가 시작하는 점은 아예 시간이 걸리지 않기때문에, 0으로 초기화
		time[N] = 0;

		// BFS를 위한 큐 선언
		Queue<Integer> q = new ArrayDeque<>();


		q.offer(N);

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur == K) {
				System.out.println(time[cur]);
				return;
			}

			int[] options = {cur - 1, cur + 1, 2 * cur};

			for(int next : options) {
				// 범위 검증 부터
				if(next < 0 || next >= size) {
					continue;
				}

				// 이미 방문한적이 있는것은
				if(time[next] != -1) {
					continue;
				}

				time[next] = time[cur] + 1;
				q.offer(next);
			}
		}



	}
}
