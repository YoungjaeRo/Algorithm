import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 자연수가 쓰여진, N장의 카드를 가지고 있다.
	 * 카드 합체 놀이:
	 * 1. x번 카드와 y번 카드를 골라 그 두장에 쓰여진 수를 더한 값을 계산한다
	 * 2. 계산한 값을 x번 카드와 y 번 카드 두장 모두에 덮어쓴다.
	 *
	 * 해당 카드놀이는 총 M 번 하면 끝이 난다.
	 * M 번의 합체를 모두 끝낸뒤, n 장의 카드에 쓰여있는 수를 모두 더한 값이 이 놀이의 점수
	 *
	 * 이 점수를 가장 작게 만드는것이 놀이의 목표
	 */

	static Long N; // 카드의 개수
	static Long M; // 더하는 횟수


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());

		// 항상 작은 두가지수를 뽑아야하기 때문에 PQ 를 사용
		PriorityQueue<Long> pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			Long a = Long.parseLong(st.nextToken());
			pq.offer(a);
		}

		// M번 카드 놀이 시행
		for(int i = 0; i < M; i++) {
			// 가장 작은 두 수 뽑기
			Long min1 = pq.poll();
			Long min2 = pq.poll();

			Long combi = min1 + min2;

			// 다시 해당 combi 값으로 덮어쓰고 난후, pq에 삽입 (2번 해야함)
			pq.offer(combi);
			pq.offer(combi);
		}

		// 합 구하기
		Long sum = 0L;
		while(!pq.isEmpty()) {
			Long num = pq.poll();
			sum = sum + num;
		}

		System.out.println(sum);

	}
}
