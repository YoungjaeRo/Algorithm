import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int max = 100001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] time = new int[max];
		boolean[] visitied = new boolean[max];


		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		visitied[N] = true;
		time[N] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(current == K) {
				System.out.println(time[current]);
				break;
			}
			
			int[] nextPositions = {current -1, current + 1, current * 2};
			
			for(int next : nextPositions) {
				if(next >= 0 && next < max && !visitied[next]) {
					visitied[next] = true;
					time[next] = time[current] + 1;
					q.offer(next);
				}
			}
		}
			
	}

}
