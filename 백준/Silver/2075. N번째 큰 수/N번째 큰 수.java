import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// PQ 안에, 지금까지 본 수 중 가장 큰 것들 N 개만 남겨둔다 ㅇㅇ
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소-힙

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());

				// 아직 N개도 안모였다면, 일단 힙에 삽입한다
				if (pq.size() < N) {
					pq.offer(num);
					
				} else { // N개 이상이 모여있다면,
					int min = pq.peek(); // 힙에 있는 가장 작은 값

					if (num > min) {
						pq.poll(); // 가장 앞의 값 빼고
						pq.offer(num);
					}
				}
			}
		}
		
		System.out.println(pq.peek());
	}
}
